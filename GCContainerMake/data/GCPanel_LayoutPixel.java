package data;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Vector;

import component_Stuff.GraphicComponent;
import deepClone.DeepClone;
import moveAndZoom.DrawingPanelMoveAndZoom;
import shape_Stuff.eShape;

public abstract class GCPanel_LayoutPixel extends GraphicComponent implements Serializable{//호호 드럽다. 
	private static final long serialVersionUID = -9220238498788652662L;
	
	//System
	boolean darkOn = false;
	boolean atDragStart = true;
	Vector<PixelTest> pixelVector;
	Vector <ItemTest>itemVector;
	ItemTest 
	currentItem,
	copyCurrentItem;
		
	//View
	int wPixelNum = 4, hPixelNum = 4, pixelW = 100, pixelH = 100, wGap = 4, hGap = 4;
	
	//Scroll
	int scrollSpeed, nowDeep=0, deepLimit=0, speedFactor = 2;// 한 픽셀의 speedFactor등분 만틈씩 움직임.
	
	//Bound
	int x=0, y=0, width, height;
	
	//Color
	Color draggingShadeColor = new Color(100,100,100,150);
	Color backGroundColor = new Color(29,31,33);
	Color seatNoticeColor = new Color(47, 53, 62);
	public void setDraggingShadeColor(Color c) {this.draggingShadeColor= c;}
	public void setBackgroundColor(Color c) {this.backGroundColor= c;}
	public void setSeatNoticeColor(Color c) {this.seatNoticeColor= c;}
	
	//Mode
	boolean autoChangeSeat = true;
	boolean itemDraggable = true;
	public void setAutoChangeSeat(boolean boo) {this.autoChangeSeat=boo;}
	public void setItemDraggable(boolean boo) {this.itemDraggable = boo;}
	
	//Pixel
	private PixelTest getPixel(int x, int y) {	return pixelVector.get(x+y*wPixelNum);}
	
	//Item
	private void refreshRect(ItemTest item) {item.setRect(getRealRectangle(item.getOwnPixel().getLocation()));}
	private Rectangle getRealRectangle(Point location) {
		return new Rectangle(x+wGap+(pixelW+wGap)*location.x, y+hGap+(pixelH+hGap)*location.y, pixelW, pixelH);
	}
	
	public GCPanel_LayoutPixel() {
		this.setAShape(eShape.rect.getAShape());
		this.setFillColor(backGroundColor);
		this.scrollSpeed = (pixelH+hGap)/speedFactor;
		this.setBorderPaint(false);
		itemVector = new  Vector <ItemTest>();
		resetView();
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
		resetView();
	}
	
	public void setPixelGap(int wGap, int hGap) {
		this.wGap = wGap;
		this.hGap = hGap;
		resetView();
	}
	
	public void setSize(int wNum, int hNum) {
		this.wPixelNum = wNum;
		this.hPixelNum = hNum;
		resetView();
	}
	
	public void setPixelSize(int w, int h) {
		this.pixelW = w;
		this.pixelH = h;
		resetView();
	}
	
	private void resetView() {
		//Update Speed
		this.scrollSpeed = (pixelH+hGap)/speedFactor;
		
		//Update Shape
		width = wGap+(pixelW+wGap)*wPixelNum;
		height = hGap+(pixelH+hGap)*hPixelNum;
		this.setShape(new Rectangle(x, y, width, height));
		
		//Update Pixel
		pixelVector = new  Vector <PixelTest>();
		for(int i=0; i<hPixelNum; i++) {
			for(int v=0; v<wPixelNum; v++) {
				pixelVector.add(new PixelTest(v,i));
				pixelVector.lastElement().setRect(x+wGap+(pixelW+wGap)*v, y+hGap+(pixelH+hGap)*i, pixelW, pixelH);
			}
		}
		
		//Update Item
		Vector <ItemTest> itemVectorTemp = new Vector <ItemTest>();
		for(ItemTest item : itemVector) {
			findSeatFor(item);
			itemVectorTemp.add(item);
		}
		itemVector.clear();
		itemVector.addAll(itemVectorTemp);
	}
	
	public void add(GraphicComponent gc) {
		ItemTest item = new ItemTest(gc);
		findSeatFor(item);
		itemVector.add(item);
	}
	
	public void findSeatFor(ItemTest item) {
		boolean findSeat = false;
		for(int i=0; i<hPixelNum; i++) {
			for(int v=0; v<wPixelNum; v++) {
				PixelTest nowPixel = getPixel(v, i);
				if(!nowPixel.isOccupied()) {
					nowPixel.setOccupied(true);
					nowPixel.setMaster(item);
					item.setOwnPixel(nowPixel);
					findSeat = true;
					break;
				}
			}
			if(findSeat) {break;}
		}
		if(!findSeat) {makeSeatFor(item);}
		refreshRect(item);
	}
	
	private void makeSeatFor(ItemTest item) {
		for(int v=0; v<wPixelNum; v++) {
			pixelVector.add(new PixelTest(v, hPixelNum));
			pixelVector.lastElement().setRect(x+wGap+(pixelW+wGap)*v, y+hGap+(pixelH+hGap)*(hPixelNum), pixelW, pixelH);
		}
		
		item.setOwnPixel(getPixel(0, hPixelNum));
		getPixel(0, hPixelNum).setOccupied(true);
		getPixel(0, hPixelNum).setMaster(item);
		deepLimit-=speedFactor;
		this.hPixelNum += 1;
	}
	
	@Override
	public void paint(Graphics2D g2d) {//TODO
		super.paint(g2d);
		
		g2d.setClip(x,y, width, height);
		
		g2d.translate(0, scrollSpeed*nowDeep);
		for(ItemTest item : itemVector) {
			item.paint(g2d);
		}
		g2d.translate(0, scrollSpeed*-nowDeep);
		
		if(darkOn) {//아이템 드래그시, 나머지 어둡게.
			g2d.setColor(draggingShadeColor);
			g2d.fill(new Rectangle(x, y, width, height));
		}
		
		g2d.translate(0, scrollSpeed*nowDeep);
		
		int gap = 20;//Pixel Occupied test
		for(PixelTest p : pixelVector) {
			if(p.isOccupied()) {g2d.setColor(Color.green);}
			else {g2d.setColor(Color.red);}
			Rectangle rect = p.getRect();
			g2d.fill(new Rectangle2D.Double(rect.getX()+gap, rect.getY()+gap, rect.getWidth()-gap*2, rect.getHeight()-gap*2));
		}
		
		if(currentItem!=null&&itemDraggable) {//드래그 중인게 갈 자리 표시
			g2d.setColor(seatNoticeColor);
			g2d.fill(currentItem.getRect());
		}
		g2d.setClip(null);
		
		if(copyCurrentItem!=null) {copyCurrentItem.paint(g2d);}
		g2d.translate(0, scrollSpeed*-nowDeep);
		
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
	
	public void mousePressed(MouseEvent e) {
		findCurrentShape(e); 
		basicAction(e);
	}
	
	public void mouseReleased(MouseEvent e) {//TODO
		actionReset(); basicAction(e);
		//if mouse not in this, drop.
	}
	
	public void mouseDragged(MouseEvent e) {
		if(copyCurrentItem!=null&&itemDraggable) {
			if(atDragStart) {dragStartAction();}
			if(autoChangeSeat) {changeSeat();}
			copyCurrentItem.processEvent(e);
		}
	}
	public void mouseMoved(MouseEvent e) {basicAction(e);}
	public void mouseClicked(MouseEvent e) {basicAction(e);}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() > 0) {wheelDownAction();}
		else if(e.getWheelRotation() < 0){wheelUpAction();}
	}
	
	public void wheelDownAction() {
		if(nowDeep>deepLimit) {
			nowDeep--;
			if(copyCurrentItem!=null) {
				Rectangle nowItem = copyCurrentItem.getRect().getBounds();
				copyCurrentItem.setRect(new Rectangle(nowItem.x, nowItem.y+scrollSpeed, nowItem.width, nowItem.height));
				changeSeat();
			}
		}
	}
	
	public void wheelUpAction() {
		if(nowDeep<0) {
			nowDeep++;
			if(copyCurrentItem!=null) {
				Rectangle nowItem = copyCurrentItem.getRect().getBounds();
				copyCurrentItem.setRect(new Rectangle(nowItem.x, nowItem.y-scrollSpeed, nowItem.width, nowItem.height));
				changeSeat();
			}
		}
	}
	
	public void basicAction(MouseEvent e) {
		for(ItemTest i : itemVector) {i.processEvent(e);}
		if(currentItem!=null) {currentItem.processEvent(e);}
		if(copyCurrentItem!=null) {copyCurrentItem.processEvent(e);}
	}

	public void findCurrentShape(MouseEvent e) {
		Point2D nowPoint = DrawingPanelMoveAndZoom.transformPoint(e.getPoint());
		nowPoint.setLocation(nowPoint.getX(), nowPoint.getY()-scrollSpeed*nowDeep);
		
		for(int i=itemVector.size()-1; i>-1; i--) {
			if(itemVector.get(i).getRect().contains(nowPoint)) {
				currentItem = itemVector.get(i);
				if(itemDraggable) {copyCurrentItem = (ItemTest) DeepClone.clone(currentItem);}
				break;
			}
		}
	}
	
	public void actionReset() {
		currentItem = null;
		copyCurrentItem = null;
		atDragStart = true;
		darkOn = false;
//		repaint();
	}
	
	public void dragStartAction() {
//			copyCurrentItem.loadShape();
			atDragStart = false;
			darkOn = true;
	}
	
	public void changeSeat() {
		Rectangle rect = copyCurrentItem.getRect().getBounds();
		Point draggingCenter = new Point(rect.x+rect.width/2, rect.y+rect.height/2);
		
		PixelTest seatPixel=null;
		for(int i=0; i<hPixelNum; i++) {
			for(int v=0; v<wPixelNum; v++) {
				if(getPixel(v,i).getRect().contains(draggingCenter)) {
					seatPixel = getPixel(v,i);
					break;
				}
			}
			if(seatPixel!=null) {break;}
		}
		
		if (seatPixel != null&&seatPixel!=currentItem.getOwnPixel()) {
			Point seatPixelPoint = seatPixel.getLocation();
			ItemTest seatPixelMaster = seatPixel.getMaster();
			Point currentItemPoint = currentItem.getOwnPixel().getLocation();
			
			PixelTest beforeMovePixel = currentItem.getOwnPixel(); 
			beforeMovePixel.setOccupied(false);
			beforeMovePixel.setMaster(null);//null아니냐? currentItem->null 했음.
			
			currentItem.setOwnPixel(getPixel(seatPixelPoint.x, seatPixelPoint.y));
			refreshRect(currentItem);
			
			PixelTest afterMovePixel =  currentItem.getOwnPixel(); 
			afterMovePixel.setOccupied(true);
			afterMovePixel.setMaster(currentItem);
			
//			itemVector.remove(currentItem);
//			itemVector.add(currentItem);//왜하는겨?
			
			if(seatPixel.isOccupied()&&currentItemPoint!=null&&seatPixelMaster!=null) {
				seatPixelMaster.setOwnPixel(getPixel(currentItemPoint.x, currentItemPoint.y));
				refreshRect(seatPixelMaster);
				seatPixelMaster.getOwnPixel().setOccupied(true);
				seatPixelMaster.getOwnPixel().setMaster(seatPixelMaster);
				itemVector.remove(seatPixelMaster);
				itemVector.add(seatPixelMaster);
			}
		}
	}
}
