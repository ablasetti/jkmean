import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;





class kmean {

	final List<CentroidCluster<DoublePoint>> clusters;
	csv_to_matrix csvm = new csv_to_matrix(new File("ooc_flat.csv"));
	Object[][] matrix = csvm.get_matrix();


	public kmean() {
		System.out.println("K MEAN++ ...");

		List<DoublePoint> points = get_d_points();

		KMeansPlusPlusClusterer<DoublePoint> jkm = new KMeansPlusPlusClusterer<DoublePoint>(10, 10000);
		System.out.println("Clustering...");
		clusters = jkm.cluster(points);

		//		int cnum = 0;
		//		for(CentroidCluster<DoublePoint> c: clusters){
		//			System.out.println("Cluster..."+ cnum);
		//			System.out.println(c.getPoints());
		//			cnum++;
		//		}

	}






	//	public List<DoublePoint> get_d_points(){
	//		List<DoublePoint> points = new ArrayList<DoublePoint>();
	//
	//
	//		for(int i=0; i< matrix.length; i++){
	//			double[] myarray = new double[matrix[i].length];
	//			for(int j=4; j< matrix[i].length; j++){
	//				myarray[j] = Double.valueOf((String)matrix[i][j]);
	//			}
	//			points.add(new DoublePoint(myarray));
	//		}
	//		return points;
	//	}




	public List<DoublePoint> get_d_points(){
		List<DoublePoint> points = new ArrayList<DoublePoint>();

		for(int i=0; i< matrix.length; i++){
			List<Double> mlist =  new ArrayList<Double>();
			for(int j=4; j< matrix[i].length; j++){
				mlist.add( Double.valueOf((String)matrix[i][j]) );
			}
			points.add(new DoublePoint(mlist.stream().mapToDouble(Double::doubleValue).toArray()));
		}
		return points;
	}



	//	public List<DoublePoint> get_d_points(){
	//		List<DoublePoint> points = new ArrayList<DoublePoint>();
	//		points.add(new DoublePoint(new double[]{1.5,2.0,3.0,6.0}));
	//		points.add(new DoublePoint(new double[]{1.8,10.0,2.0,3.0}));
	//		points.add(new DoublePoint(new double[]{3.5,6.7,2.2,2.2}));
	//		
	//		return points;
	//	}



	public int get_cluster_by_point(DoublePoint p) {
		//		System.out.println("Get clust num...");
		int clust = -1;

		int cnum = 0;
		for(CentroidCluster<DoublePoint> c: clusters){
			for(DoublePoint dp : c.getPoints()) {
				//				System.out.println(dp);
				if(dp.equals(p)) {
					//					System.out.println(dp);
					clust = cnum;
					return clust;
				}
			}

			cnum++;
		}

		return clust;
	}





	public void classify() {
		for(int i=0; i< matrix.length; i++){
			List<Double> mlist =  new ArrayList<Double>();
			for(int j=4; j< matrix[i].length; j++){
				mlist.add( Double.valueOf((String)matrix[i][j]) );
			}
			DoublePoint dp = new DoublePoint(mlist.stream().mapToDouble(Double::doubleValue).toArray());
			System.out.println( (String)matrix[i][0] +","+ 
								(String)matrix[i][1] +","+ 
								(String)matrix[i][2] +","+ 
								(String)matrix[i][3] +","+ 
								get_cluster_by_point(dp));
		}
	}




	public static void main(String[] args) {
		kmean  km = new kmean();
		km.classify();
	}




}