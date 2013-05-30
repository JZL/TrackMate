package fiji.plugin.trackmate.gui.panels.detector;

import static fiji.plugin.trackmate.detection.DetectorKeys.KEY_DOWNSAMPLE_FACTOR;
import static fiji.plugin.trackmate.detection.DetectorKeys.KEY_RADIUS;
import static fiji.plugin.trackmate.detection.DetectorKeys.KEY_TARGET_CHANNEL;
import static fiji.plugin.trackmate.detection.DetectorKeys.KEY_THRESHOLD;
import static fiji.plugin.trackmate.gui.TrackMateWizard.FONT;
import fiji.plugin.trackmate.Model;
import fiji.plugin.trackmate.detection.DownsampleLogDetectorFactory;
import fiji.plugin.trackmate.detection.SpotDetectorFactory;
import fiji.plugin.trackmate.gui.panels.components.JNumericTextField;
import ij.ImagePlus;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class DownSampleLogDetectorConfigurationPanel extends LogDetectorConfigurationPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jLabelDownSample;
	private JNumericTextField jTextFieldDownSample;

	/*
	 * CONSTRUCTOR
	 */
	
	public DownSampleLogDetectorConfigurationPanel(ImagePlus imp, Model model) {
		super(imp, DownsampleLogDetectorFactory.INFO_TEXT, DownsampleLogDetectorFactory.NAME, model);
	}

	/*
	 * METHODS
	 */
	
	@SuppressWarnings("rawtypes")
	@Override
	protected SpotDetectorFactory<?> getDetectorFactory() {
		return new DownsampleLogDetectorFactory();
	}
	
	
	@Override
	protected void initGUI() {
		super.initGUI();
		this.setPreferredSize(new java.awt.Dimension(300, 461));
		// Remove sub-pixel localization checkbox
		remove(jCheckSubPixel);
		remove(jCheckBoxMedianFilter);
		
		// Add down sampling text and textfield
		{
			jLabelDownSample = new JLabel();
			jLabelDownSample.setLocation(16, 290);
			jLabelDownSample.setSize(175, 16);
			jLabelDownSample.setText("Downsampling factor:");
			jLabelDownSample.setFont(FONT);
			add(jLabelDownSample);
		}
		{
			jTextFieldDownSample = new JNumericTextField();
			jTextFieldDownSample.setHorizontalAlignment(SwingConstants.CENTER);
			jTextFieldDownSample.setText("1");
			jTextFieldDownSample.setLocation(168, 290);
			jTextFieldDownSample.setSize(40, 16);
			jTextFieldDownSample.setFont(FONT);
			add(jTextFieldDownSample);
		}
	}
	
	
	@Override
	public Map<String, Object> getSettings() {
		Map<String, Object> settings = new HashMap<String, Object>(5);
		int targetChannel = sliderChannel.getValue();
		double expectedRadius = Double.parseDouble(jTextFieldBlobDiameter.getText())/2;
		double threshold = Double.parseDouble(jTextFieldThreshold.getText());
		int downsamplefactor = Integer.parseInt(jTextFieldDownSample.getText());
		settings.put(KEY_TARGET_CHANNEL, targetChannel);
		settings.put(KEY_RADIUS, expectedRadius);
		settings.put(KEY_THRESHOLD, threshold);
		settings.put(KEY_DOWNSAMPLE_FACTOR, downsamplefactor);
		return settings;
	}
	
	@Override
	public void setSettings(Map<String, Object> settings) {
		sliderChannel.setValue((Integer) settings.get(KEY_TARGET_CHANNEL));
		jTextFieldBlobDiameter.setText(""+( 2 * (Double) settings.get(KEY_RADIUS)));
		jTextFieldThreshold.setText("" + settings.get(KEY_THRESHOLD));
		jTextFieldDownSample.setText(""+settings.get(KEY_DOWNSAMPLE_FACTOR));
	}
}
