package cs131.pa1.filter.sequential;

import cs131.pa1.filter.Message;

public class GrepSequentialFilter extends SequentialFilter {

	private String reg;
	
	
	public GrepSequentialFilter(String command) {
		String [] commands = command.split(" ");
		if (commands.length < 2) {
			output.add(String.format(Message.REQUIRES_PARAMETER.toString(), "grep"));
			input.clear();
			return;
		}
		reg = commands[1];
	}


	@Override
	protected String processLine(String line) {
		if (line.contains(reg)) {
			return line.trim();
		}
		return null;
	}

}
