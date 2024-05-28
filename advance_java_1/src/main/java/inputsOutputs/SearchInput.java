package inputsOutputs;

import attributes.*;

public class SearchInput {
	
	private Colors color;
	private Size size;
	private Gender gender;
	private OutputPreference outputPreference;
	
	
	public Colors getColor() {
		return color;
	}
	public void setColor(Colors color) {
		this.color = color;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public OutputPreference getOutputPreference() {
		return outputPreference;
	}
	public void setOutputPreference(OutputPreference outputPreference) {
		this.outputPreference = outputPreference;
	}

}
