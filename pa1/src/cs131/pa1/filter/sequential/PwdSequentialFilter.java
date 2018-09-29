package cs131.pa1.filter.sequential;


public class PwdSequentialFilter extends SequentialFilter{

	@Override
	public void process() {
		System.out.println(SequentialREPL.currentWorkingDirectory);
	}

	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
