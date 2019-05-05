import java.awt.Color;
import java.awt.Graphics;

public class drawWires {
	int gridX1 = 0;
	int gridY1 = 0;
	int gridX2 = 0;
	int gridY2 = 0;
	Color color = Color.RED;
	Gate parent = null;
	Gate child = null;
	
	public drawWires(int gridX1, int gridY1, int gridX2, int gridY2)
	{
		this.gridX1 = gridX1+20; 
		this.gridY1 = gridY1+25;
		this.gridX2 = gridX2+20;
		this.gridY2 = gridY2+25;
	}
	
	public int getX1()
	{
		return gridX1;
	}
	public int getX2()
	{
		return gridX2;
	}
	public int getY1()
	{
		return gridY1;
	}
	public int getY2()
	{
		return gridY2;
	}
	public Color getColor()
	{
		return color;
	}
	public Gate parentGate()
	{
		return parent;
	}
	public Gate childGate()
	{
		return child;
	}
}
