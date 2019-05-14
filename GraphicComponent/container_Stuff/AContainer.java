package container_Stuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import javax.swing.JPanel;

import affineScrollPanel.AffineScrollPanel;
import component_Stuff.GraphicComponent;
import deepClone.DeepClone;
import dragAndDrop.DragAndDropManager;
import view.DrawingPanel;

@SuppressWarnings("serial")
public abstract class AContainer extends AffineScrollPanel {//호호 드럽다. 

	Color darkColor = new Color(100,100,100,150);//아이템 움직일때 덧칠색
	Color backGroundColor = new Color(29,31,33);//배경색
	Color seatColor = new Color(47, 53, 62);//아이템이 갈 자리 표시색
	
	int limitXPixel, limitYPixel;
	int pixelW, pixelH, gapW = 4, gapH = 4;
	int basicPixelW =1,  basicPixelH =1;
	
	DrawingPanel master;
	Image myImg; 
	Vector<Pixel> pixelVector;
	Vector <Item>itemVector;
	Item currentItem, copyCurrentItem;
	boolean autoChangeSeat = true;
	boolean mouseOnThis = false;
	boolean atDragStart = true;
	boolean draggable = true;
	boolean darkOn = false;
	
	public AContainer(int pixelWSize, int pixelHSize, int wPixelNum, int hPixelNum) {
		this.limitXPixel = wPixelNum;
		this.limitYPixel = hPixelNum;
		this.pixelW = pixelWSize;
		this.pixelH = pixelHSize;
		this.setSize(limitXPixel*(pixelW+gapW)+gapW, limitYPixel*(pixelH+gapH)+gapH);
		
		this.setSpeed((pixelH+gapH)/2);
		
		MouseHandler mh = new MouseHandler();
		this.addMouseListener(mh);
		this.addMouseMotionListener(mh);
		
		itemVector = new  Vector <Item>();
		pixelVector = new  Vector <Pixel>();
		
		for(int i=0; i<limitYPixel; i++) {
			for(int v=0; v<limitXPixel; v++) {
				pixelVector.add(new Pixel(v,i));
				pixelVector.lastElement().setRect(gapW+(pixelW+gapW)*v, gapH+(pixelH+gapH)*i, pixelW, pixelH);
			}
		}
	}

	public boolean canIseat(int pixelX, int pixelY, int wPixelNum, int hPixelNum) {
		boolean result = true;
		for(int i=0; i<hPixelNum; i++) {
			for(int v=0; v<wPixelNum; v++) {
				if(pixelX+v<limitXPixel&& pixelY+i<limitYPixel) {
					if(getPixel(pixelX+v, pixelY+i).isOccupied()) {result = false;break;}
				}else {result = false;break;}
			}
			if(!result) {break;}
		}
		return result;
	} 
	
	public void findSeatFor(Item item) {//여기 오기전에, 아이템 사이즈가 정해져 있어야 함.
		boolean findSeat = false;
		for(int i=0; i<limitYPixel; i++) {
			for(int v=0; v<limitXPixel; v++) {
				if(canIseat(v, i, item.getW(), item.getH())) {
					pixelOccupied(v, i, item.getW(), item.getH(), true, item);
					item.setPoint(v, i);
					findSeat = true;
					break;
				}
			}
			if(findSeat) {break;}
		}
		if(!findSeat) {makeSeatFor(item);}
		refreshRect(item);
	}
	
	private void makeSeatFor(Item item) {
		for(int i=0; i<item.getH(); i++) {//add new Pixel
			for(int v=0; v<limitXPixel; v++) {
				pixelVector.add(new Pixel(v,limitYPixel+i));
				pixelVector.lastElement().setRect(gapW+(pixelW+gapW)*v, gapH+(pixelH+gapH)*(limitYPixel+i), pixelW, pixelH);
			}
		}
		item.setPoint(0, limitYPixel);
		pixelOccupied(0, limitYPixel, item.getW(), item.getH(), true, item);
		this.deeper(item.getH()*2);
		this.limitYPixel += item.getH();
	}
	
	private void pixelOccupied(int x, int y, int w, int h, boolean occupied, Item master) {
		try {
			for(int i=0; i<h; i++) {
				for(int v=0; v<w; v++) {
					getPixel(x+v, y+i).setOccupied(occupied);
					if(occupied) {getPixel(x+v, y+i).setMaster(master);}
					else {getPixel(x+v, y+i).setMaster(findNewMaster(getPixel(x+v, y+i), master));}
				}
			}
		}catch(Exception e) {}//아이템이 픽셀 아래로 가도 상관 없게 하는 것.
	}

	private Item findNewMaster(Pixel pixel, Item master) {
		for(Item i : itemVector) {
			if(i.getRect().intersects(pixel.getRect())&&i!=master) {
				pixel.setOccupied(true);
				return i;
			}
		}
		return null;
	}

	public void setAutoChangeSeat(boolean boo) {this.autoChangeSeat=boo;}
	public void setBackgroundColor(Color c) {this.backGroundColor= c;}
	public void setItemDraggable(boolean boo) {this.draggable = boo;}
	public void setGapW(int gapW) {this.gapW=gapW;this.setSize(limitXPixel*(pixelW+gapW)+gapW, limitYPixel*(pixelH+gapH)+gapH);}
	public void setGapH(int gapH) {this.gapH=gapH;this.setSize(limitXPixel*(pixelW+gapW)+gapW, limitYPixel*(pixelH+gapH)+gapH);}
	public void addItem(GraphicComponent gc) {
		Item item = new Item(gc);
		item.setSize(basicPixelW, basicPixelH);
		findSeatFor(item);
		itemVector.add(item);
	}
	public void removeItem(Item item) {itemVector.remove(item);}
	public void setItemSize(Item item, int w, int h) {item.setSize(w, h); refreshRect(item);}
	public void setItemPoint(Item item, int x, int y) {item.setSize(x, y); refreshRect(item);}
	private void refreshRect(Item item) {item.setRect(getRealRectangle(item.getX(), item.getY(), item.getW(), item.getH()));}
	private Pixel getPixel(int x, int y) {	return pixelVector.get(x+y*limitXPixel);}
	public Rectangle getRealRectangle(int x, int y, int w, int h) {
		return new Rectangle(gapW+(pixelW+gapW)*x, gapH+(pixelH+gapH)*y, pixelW+(pixelW+gapW)*(w-1), pixelH+(pixelH+gapH)*(h-1));
	}
	
	public Image getImg() {return myImg;}//affine Transform적용시 발생하는 에러를 막기 위함. 더블 버퍼링 역할도 하나?
	public void myPaint(Graphics g) {
		this.repaint();
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(this.getImg(), this.getX(), this.getY(), null);
//		System.out.println(copyCurrentItem!=null);
//		if(copyCurrentItem!=null) {
//			master.repaint();
//		}
	}
	
	public Item getCopy() {return copyCurrentItem;}//TODO
	public void addMaster(DrawingPanel p) {
		master=p;
	}
	
	public Image makeImg() {
		myImg = this.createImage(this.getWidth(),this.getHeight());
		Graphics2D img_g = (Graphics2D)myImg.getGraphics();
		img_g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		img_g.setColor(backGroundColor);
		img_g.fillRect(0, 0, getWidth(), getHeight());
		
		img_g.setTransform(scrollAT);
		for(Item item : itemVector) {item.paint(img_g);}
		
//		for(Pixel p : pixelVector) { //픽셀의 점유? 움직임? 테스트용.
//			img_g.setColor(Color.red);
//			if(p.isOccupied()) {img_g.setColor(Color.green);}
//			img_g.fill(p.getRect());
//		}
		
		img_g.setTransform(new AffineTransform());
		if(darkOn) {//아이템 드래그시, 나머지 어둡게.
			img_g.setColor(darkColor);
			img_g.fill(new Rectangle(0,0,this.getWidth(),this.getHeight()));
		}
		img_g.setTransform(scrollAT);
		if(currentItem!=null&&draggable) {
			img_g.setColor(seatColor);
			img_g.fill(currentItem.getRect());
		}
		
//		if(copyCurrentItem!=null) {copyCurrentItem.paint(img_g);}//카피 아이템을 움직이는 것처럼 하기.
		return myImg;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(makeImg(), 0, 0, null);
		if(copyCurrentItem!=null) {
			master.repaint();
		}
	}
	
	public JPanel getContainerPanel() {return this;}
	public class MouseHandler implements MouseListener, MouseMotionListener{//TODO
		public void mousePressed(MouseEvent e) {findCurrentShape(e);basicAction(e);}
		public void mouseReleased(MouseEvent e) {
			DragAndDropManager.drop();
			basicAction(e);
			reset();
		}
		public void mouseClicked(MouseEvent e) {basicAction(e);}
		public void mouseDragged(MouseEvent e) {
			if(copyCurrentItem!=null&&draggable) {
				dragStartAction();
				if(autoChangeSeat) {changeSeat();}
				copyCurrentItem.processEvent(e);
			}
			repaint();
		}
		public void mouseMoved(MouseEvent e) {basicAction(e);}
		public void mouseEntered(MouseEvent e) {basicAction(e); mouseOnThis = true;DragAndDropManager.setNowMouseOnPanel(getContainerPanel());}
		public void mouseExited(MouseEvent e) {basicAction(e); mouseOnThis = false;}
	}

	public void wheelDownAction() {
		super.wheelDownAction();
		if(copyCurrentItem!=null) {
			Rectangle nowItem = copyCurrentItem.getRect().getBounds();
			copyCurrentItem.setRect(new Rectangle(nowItem.x, nowItem.y+this.getSpeed(), nowItem.width, nowItem.height));
			changeSeat();
		}
	}
	
	public void wheelUpAction() {
		super.wheelUpAction();
		if(copyCurrentItem!=null) {
			Rectangle nowItem = copyCurrentItem.getRect().getBounds();
			copyCurrentItem.setRect(new Rectangle(nowItem.x, nowItem.y-this.getSpeed(), nowItem.width, nowItem.height));
			changeSeat();
		}
	}
	
	public void basicAction(MouseEvent e) {
		for(Item i : itemVector) {i.processEvent(e);}
		if(currentItem!=null) {currentItem.processEvent(e);}
		if(copyCurrentItem!=null) {copyCurrentItem.processEvent(e);}
		repaint();
	}

	public void findCurrentShape(MouseEvent e) {
		for(int i=itemVector.size()-1; i>-1; i--) {
			if(itemVector.get(i).getRect().contains(transformPoint(e.getPoint()))) {
				currentItem = itemVector.get(i);
				if(draggable) {copyCurrentItem = (Item) DeepClone.clone(currentItem);}
				break;
			}
		}
	}
	public void reset() {
		currentItem = null;
		copyCurrentItem = null;
		atDragStart = true;
		darkOn = false;
		repaint();
	}
	
	public void dragStartAction() {
		if(atDragStart) {
			
			DragAndDropManager.setDraggingComponent(copyCurrentItem.getGraphicComponent());
			DragAndDropManager.setComponentMasterPanel(this);
			
			atDragStart = false;
			darkOn = true;
		}
	}
	
	public void changeSeat() {
		Rectangle rect = copyCurrentItem.getRect().getBounds();
		Point startPoint = new Point(rect.x+rect.width/2, rect.y+rect.height/2);
		
		Pixel startPixel=null;
		for(int i=0; i<limitYPixel; i++) {
			for(int v=0; v<limitXPixel; v++) {
				if(getPixel(v,i).getRect().contains(startPoint)) {
					startPixel = getPixel(v,i);
					break;
				}
			}
			if(startPixel!=null) {break;}
		}
		
		if (startPixel != null) {
			Point p = startPixel.getLocation();
			Item startPixelMaster = startPixel.getMaster();
			Point currentItemPoint = new Point(currentItem.getX(), currentItem.getY());
			
			pixelOccupied(currentItem.getX(), currentItem.getY(), currentItem.getW(), currentItem.getH(), false,currentItem);
			currentItem.setPoint(p.x, p.y);
			refreshRect(currentItem);
			pixelOccupied(currentItem.getX(), currentItem.getY(), currentItem.getW(), currentItem.getH(), true,currentItem);
			removeItem(currentItem);
			itemVector.add(currentItem);
			
			if(startPixel.isOccupied()&&basicPixelW==1&&basicPixelH==1&&currentItemPoint!=null&&startPixelMaster!=null) {
				//요것은 픽셀 사이 간격에 아이템의 시작점이 위치한 경우, 바꿀 아리템이 없는 경우, 안한다.
				startPixelMaster.setPoint(currentItemPoint.x, currentItemPoint.y);
				refreshRect(startPixelMaster);
				pixelOccupied(startPixelMaster.getX(), startPixelMaster.getY(), startPixelMaster.getW(), startPixelMaster.getH(), true,startPixelMaster);
				removeItem(startPixelMaster);
				itemVector.add(startPixelMaster);
			}
		}
	}
}
