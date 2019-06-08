package fTime;

import java.awt.Color;
import java.io.Serializable;

import zStuff_Time.ATimeFunction;

public class FRainbow extends ATimeFunction implements Serializable{
	private static final long serialVersionUID = -6743524881365403749L;
	
	@Override
	public void timeAction() {
		master.setFillColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
		master.setBorderColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
	}
	
}
