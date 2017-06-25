package queryParser;

public interface IChain {

	public void setNextInChain(IChain nextInChain);
	public void parseQuery(String userQuery);
	
}
