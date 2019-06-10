package zStuff_GCPanel_LayoutPixel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Vector;

import calculation.SuperVector;
import deepClone.DeepClone;
import onOff.Debug;
import zStuff_GraphicComponent.GraphicComponent;
import zStuff_Shape.eShape;

public abstract class GCPanel_LayoutPixel_Y extends GraphicComponent implements Serializable{//호호 드럽다. 
	private static final long serialVersionUID = -9220238498788652662L;
	
	boolean shadow = true;
	public void setShadow(boolean boo) {shadow = boo;}
	boolean push = false;
	public void setPush(boolean boo) {push = boo;}
	
	//System
	boolean dragShadeOn = false;
	boolean firstDrag = true;
	protected Vector<Pixel> pixelVector;
	protected Vector <Item>itemVector;
	private Item currentItem;

	Item copyCurrentItem;
		
	//View
	int 
	wPixelNum = 4, hPixelNum = 4, 
	pixelW = 100, pixelH = 100, wGap = 4, hGap = 4;
	
	//Scroll
	int scrollSpeed, nowDeep=0, deepLimit=0, speedFactor = 2;// 한 픽셀의 speedFactor등분 만큼씩 움직임.
	
	//Bound
	int x=0, y=0, width, height;
	public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return wGap+(pixelW+wGap)*wPixelNum;}
	public int getHeight() {return hGap+(pixelH+hGap)*hPixelNum;}
	
	//Color
	Color draggingShadeColor = new Color(100,100,100,150);
	Color backGroundColor = new Color(29,31,33);
	Color seatNoticeColor = new Color(47, 53, 62);
	public void setDraggingShadeColor(Color c) {this.draggingShadeColor= c;}
	public void setBackgroundColor(Color c) {this.backGroundColor= c; this.setFillColor(backGroundColor);}
	public void setSeatNoticeColor(Color c) {this.seatNoticeColor= c;}
	
	//Mode
	boolean autoChangeSeat = true;
	boolean itemDraggable = true;
	public void setAutoChangeSeat(boolean boo) {this.autoChangeSeat=boo;}
	public void setItemDraggable(boolean boo) {this.itemDraggable = boo;}
	
	//Pixel
	protected Pixel getPixel(int x, int y) {
		try {return pixelVector.get(x+y*wPixelNum);}
		catch(Exception e) {}
		return null;
	}
	
	//Item
	protected void refreshRect(Item item) {item.setShape(getRealRectangle(item.getOwnPixel().getLocation()));}
	private Rectangle getRealRectangle(Point location) {
		return new Rectangle(x+wGap+(pixelW+wGap)*location.x, y+hGap+(pixelH+hGap)*location.y, pixelW, pixelH);
	}
	
	public GCPanel_LayoutPixel_Y() {
		this.setAShape(eShape.rect.getAShape());
		this.setFillColor(backGroundColor);
		this.scrollSpeed = (pixelH+hGap)/speedFactor;
		this.setBorderPaint(false);
		itemVector = new  Vector <Item>();
		resetView();
		this.saveClip();
	}

	public void setGCLocation(int x, int y) {
		this.x = x;
		this.y = y;
		resetView();
		this.saveClip();
	}
	
	public void setPixelGap(int wGap, int hGap) {
		this.wGap = wGap;
		this.hGap = hGap;
		resetView();
		this.saveClip();
	}
	
	public void setSize(int wNum, int hNum) {
		this.wPixelNum = wNum;
		this.hPixelNum = hNum;
		resetView();
		this.saveClip();
	}
	
	public void setPixelSize(int w, int h) {
		this.pixelW = w;
		this.pixelH = h;
		resetView();
		this.saveClip();
	}
	
	Shape myClip; int relax =2;
	public void saveClip() {myClip = new Rectangle(x,y, width+relax, height);}
	public void setClip(Shape newClip) {myClip = newClip;}
	public Shape getClip() {return myClip;}
	
	protected void resetView() {
		//Update Speed
		this.scrollSpeed = (pixelH+hGap)/speedFactor;
		
		//Update Shape
		width = wGap+(pixelW+wGap)*wPixelNum;
		height = hGap+(pixelH+hGap)*hPixelNum;
		this.setShape(new Rectangle(x, y, width, height));
		
		//Update Pixel
		pixelVector = new  Vector <Pixel>();
		for(int i=0; i<hPixelNum; i++) {
			for(int v=0; v<wPixelNum; v++) {
				pixelVector.add(new Pixel(v,i));
				pixelVector.lastElement().setRectByXYWH(x+wGap+(pixelW+wGap)*v, y+hGap+(pixelH+hGap)*i, pixelW, pixelH);
			}
		}
		
		//Update Item
		
		//maintain Item way
		Vector <Item> itemVectorTemp = new Vector <Item>();//0604
		for(Item item : itemVector) {
			itemVectorTemp.add(item);
			findSeatFor(item);
		}
		itemVector.clear();
		itemVector = itemVectorTemp;
		
		//all Clear Way
//		itemVector = new Vector <Item>();
//		for(Item item : itemVector) {
//			findSeatFor(item);
//		}
//		itemVector.clear();
	}
	
	public void add(GraphicComponent gc) {
		Item item = new Item(gc);
		findSeatFor(item);
		itemVector.add(item);
	}
	
	public void add(int i, GraphicComponent gc) {//TODO
		Item item = new Item(gc);
		findSeatFor(item);
		itemVector.add(i,item);
		resetFollowVector();
	}
	
	public Vector<Item> getItems() {
		return itemVector;
	}
	
	public void findSeatFor(Item item) {
		boolean findSeat = false;
		int nowItemNum = itemVector.size();
		Pixel seat = getPixel(nowItemNum%wPixelNum, nowItemNum/wPixelNum);
		if(seat!=null) {item.setOwnPixel(seat);}
		else {if(!findSeat) {makeSeatFor(item);}}
		refreshRect(item);
	}
	
	private void makeSeatFor(Item item) {
		for(int v=0; v<wPixelNum; v++) {
			pixelVector.add(new Pixel(v, hPixelNum));
			pixelVector.lastElement().setRectByXYWH(x+wGap+(pixelW+wGap)*v, y+hGap+(pixelH+hGap)*(hPixelNum), pixelW, pixelH);
		}
		item.setOwnPixel(getPixel(0, hPixelNum));
		deepLimit-=speedFactor;
		this.hPixelNum += 1;
	}
	
	@Override
	public void paint(Graphics2D g2d) {//TODO
		g2d.setClip(myClip);
		super.paint(g2d);
		for(Item item : itemVector) {item.paint(g2d);}
		if(shadow&&dragShadeOn) {//아이템 드래그시, 나머지 어둡게.
			g2d.setColor(draggingShadeColor);
			g2d.fill(this.getShape());
		}
		
		if(Debug.isOn()) {
			g2d.setClip(null);
			int gap = 20;//Pixel Occupied test
			g2d.setColor(Color.red);
			for(Item p : itemVector) {
				Rectangle rect = p.getOwnPixel().getRect().getBounds();
				g2d.fill(new Rectangle2D.Double(rect.getX()+gap, rect.getY()+gap, rect.getWidth()-gap*2, rect.getHeight()-gap*2));
			}g2d.setClip(myClip);
		}
		
		if(getCurrentItem()!=null&&itemDraggable) {//드래그 중인게 갈 자리 표시
			g2d.setColor(seatNoticeColor);
			g2d.setStroke(new BasicStroke(getCurrentItem().getInContainerGC().getBorderThick()+1));
			g2d.fill(getCurrentItem().getShape());
			g2d.draw(getCurrentItem().getShape());
		}
		
		g2d.setClip(null);
		if(copyCurrentItem!=null) {copyCurrentItem.paint(g2d);}
	}
	
	public void mousePressed(MouseEvent e) {
		findCurrentShape(e); 
		if(getCurrentItem()!=null) {getCurrentItem().processEvent(e);}
		if(copyCurrentItem!=null) {copyCurrentItem.processEvent(e);}
	}
	
	public void mouseReleased(MouseEvent e) {//TODO
		basicAction(e);
		actionReset(); 
		//if mouse not in this, drop.
	}
	
	public void mouseDragged(MouseEvent e) {
		if(copyCurrentItem!=null&&itemDraggable) {
			if(firstDrag) {dragStartAction();}
			if(autoChangeSeat) {changeSeat();}
			copyCurrentItem.processEvent(e);
		}
	}
	public void mouseMoved(MouseEvent e) {basicAction(e);}
	public void mouseClicked(MouseEvent e) {basicAction(e);}
	
	public void mouseWheelMoved(MouseWheelEvent e) {//TODO
		if (e.getWheelRotation() > 0) {if(nowDeep>deepLimit) {wheelAction(-1);}}
		else if(e.getWheelRotation() < 0){if(nowDeep<0) {wheelAction(1);}}
		if(getCurrentItem()!=null) {getCurrentItem().processEvent(e);}
	}
	
	public void wheelAction(int i) {
		nowDeep+= i;
		y+=scrollSpeed*i;
		
		AffineTransform at = new AffineTransform();
		at.translate(0, scrollSpeed*i);
		
		//Update Pixel
		for(Pixel pixel : pixelVector) {
			pixel.setRectByShape(at.createTransformedShape(pixel.getRect()));
		}
		
		//Update Item
		for(Item item : itemVector) {
			item.setShape(item.getOwnPixel().getRect());
		}
		
		if(copyCurrentItem != null) {
			changeSeat();
		}
	}
	
	public void basicAction(MouseEvent e) {
		for(Item i : itemVector) {
			if(i!=getCurrentItem()) {
				i.processEvent(e);
			}
		}
		if(getCurrentItem()!=null) {getCurrentItem().processEvent(e);}
		if(copyCurrentItem!=null) {copyCurrentItem.processEvent(e);}
	}

	public void findCurrentShape(MouseEvent e) {
		Point2D nowPoint = e.getPoint();
		
		for(int i=itemVector.size()-1; i>-1; i--) {
			if(itemVector.get(i).getShape().contains(nowPoint)) {
				setCurrentItem(itemVector.get(i));
				if(itemDraggable) {copyCurrentItem = (Item) DeepClone.clone(getCurrentItem());}
				break;
			}
		}
		for(Item item : itemVector) {if(item!=getCurrentItem()) {item.getInContainerGC().setSelected(false);}}
		if(getCurrentItem()!=null) {getCurrentItem().getInContainerGC().setSelected(true);}
	}
	
	public void dragStartAction() {
		firstDrag = false;
		dragShadeOn = true;
	}
	
	public void actionReset() {
		setCurrentItem(null);
		copyCurrentItem = null;
		firstDrag = true;
		dragShadeOn = false;
	}
	
	@Override
	public void processEvent(MouseEvent e) {
		switch(e.getID()) {
		case MouseEvent.MOUSE_PRESSED : this.mousePressed(e); break;
		case MouseEvent.MOUSE_RELEASED : this.mouseReleased(e); break;
		case MouseEvent.MOUSE_CLICKED : this.mouseClicked(e); break;
		case MouseEvent.MOUSE_DRAGGED : this.mouseDragged(e); break;
		case MouseEvent.MOUSE_MOVED : this.mouseMoved(e); break;
		case MouseEvent.MOUSE_ENTERED : this.mouseEntered(e); break;
		case MouseEvent.MOUSE_EXITED : this.mouseExited(e); break;
		case MouseEvent.MOUSE_WHEEL : this.mouseWheelMoved((MouseWheelEvent) e); break;
		default : break;
		}
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public Point changeSeat() {
		Point returnPoint = null;
		Rectangle rect = copyCurrentItem.getShape().getBounds();
		Point draggingCenter = new Point(rect.x+rect.width/2, rect.y+rect.height/2);
		
		Pixel seatPixel=null;
		for(int i=0; i<hPixelNum; i++) {
			for(int v=0; v<wPixelNum; v++) {
				if(getPixel(v,i).getRect().contains(draggingCenter)) {
					seatPixel = getPixel(v,i);
					break;
				}
			}
			if(seatPixel!=null) {break;}
		}
		if (seatPixel != null&&seatPixel!=getCurrentItem().getOwnPixel()&&pushCondition(seatPixel)) {
			Point currentItemPoint = getCurrentItem().getOwnPixel().getLocation();
			
			int currentItemVectorNum = pixelLocationToVectorNum(getCurrentItem().getOwnPixel());
			int seatPixelVectorNum = pixelLocationToVectorNum(seatPixel);
			if(seatPixelVectorNum<itemVector.size()) {
				SuperVector.change(itemVector, currentItemVectorNum, seatPixelVectorNum);
				resetFollowVector();
				returnPoint = new Point(currentItemPoint.getLocation().y, seatPixel.getLocation().y);
			}
		}
		return returnPoint;
	}
	
	protected void resetFollowVector() {
		for(Item item : itemVector) {
			Pixel ownPixel = findPixelByVectorNum(itemVector.indexOf(item));
			item.setOwnPixel(ownPixel);
			refreshRect(item);
		}
	}
	
	private int pixelLocationToVectorNum(Pixel pixel) {
		Point p = pixel.getLocation();
		return p.x+p.y*wPixelNum;
	}
	
	private Pixel findPixelByVectorNum(int num) {
		Pixel pixel = getPixel(num%wPixelNum, num/wPixelNum);
		return pixel;
	}
	
	private boolean pushCondition(Pixel seatPixel) {
		if(push) {
			return itemVector.size()>pixelLocationToVectorNum(seatPixel);
		}
		else {return true;}
	}
	public Item getCurrentItem() {return currentItem;}
	public void setCurrentItem(Item currentItem) {this.currentItem = currentItem;}
}
