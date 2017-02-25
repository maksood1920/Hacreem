package main.java.dao;

import java.util.List;

import main.java.pathfinding.ExampleNode;

public interface InfDao {

	 int savePath(List<ExampleNode> path);
	public void cost(int cost, int pk);
	 void custFeedback(String customerFd, int pk );
	 void drvierFeedback(String diverFd, int pk);
	
	 void CustomeRating(int rating, int pk );
	 void driveRating(int rating, int pk);
	
		// save the rating of driver
	
	
}
