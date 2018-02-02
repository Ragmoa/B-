public class Case 
{
	private Content typeC;

	public Case(Content type) 
	{
		typeC=type;
	}

	public Content getTypeC() 
	{
		return typeC;
	}

	public void setTypeC(Content typeC) 
	{
		this.typeC = typeC;
	}
	
	public String toString()
	{
		return typeC.name();
	}
}
