package com.antiban.camera;

import com.antiban.Antiban;
import com.antiban.TimedAntiban;
import com.util.PropertiesEx;

public class XCameraAntiban extends TimedAntiban implements Antiban {

	public int minimumAngleRotation = 30;
	public int maximumAngleRotation = 180;

	@Override
	public PropertiesEx getProperties() {
		PropertiesEx p = super.getProperties();
		p.setProperty("MinimumAngleRotation",
				Integer.toString(minimumAngleRotation));
		p.setProperty("MaximumAngleRotation",
				Integer.toString(maximumAngleRotation));
		return p;
	}

	@Override
	public void setProperties(PropertiesEx p) {
		super.setProperties(p);
		minimumAngleRotation = p.Int("MinimumAngleRotation");
		maximumAngleRotation = p.Int("MaximumAngleRotation");
	}

	@Override
	public void run() {
		while (isAlive()) {
			
		}
	}

}
