package cs131.pa1.filter.sequential;

public class UniqSequentialFilter extends SequentialFilter {

	@Override
	protected String processLine(String line) {
		if (output.contains(line)) {
			return null;
		}
		return line;
	}

}
