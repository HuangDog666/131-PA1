package cs131.pa1.filter.sequential;

public class OutputSequentialFilter extends SequentialFilter {

	@Override
	protected String processLine(String line) {
		System.out.println(line);
//		SequentialREPL.output = true;
		return null;
	}
}
