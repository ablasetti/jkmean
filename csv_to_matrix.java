import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;



public class csv_to_matrix {
	
		Object[][] mymatrix;


		public csv_to_matrix(File mycsv)  {

			try {
				BufferedReader br2;
				br2 = new BufferedReader(new FileReader(mycsv));

				String REGEX=",";
				Pattern pattern = Pattern.compile(REGEX);
				String input;

				int rc = 0 ;
				String[] split = null;
				while(( (input = br2.readLine()) != null))
				{
					if(rc == 0)
						split = pattern.split(input);

					rc++;
				}
				br2.close();

				System.out.println("Row count " + rc);
				if(rc==0) {
					mymatrix  = new Object[0][0];
					return;
				}
				
				System.out.println("Matrix 1 lenght " + split.length);
				mymatrix  = new Object[rc][split.length];

				BufferedReader br3 = new BufferedReader(new FileReader(mycsv));
				String[] split2 = null;
				int rc2=0;
				while(( (input = br3.readLine()) != null))
				{
					split2 = pattern.split(input);
//					System.out.println("Matrix 2 lenght " + split2.length);
					for(int i =0; i < split2.length; i++)
					{

						//if(split2[i].equals("") || split2[i].equals(null))
						//	split2[i] = "none";

						mymatrix[rc2][i] = split2[i];
					}

					rc2++;
				}
				br3.close();

			} catch ( IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		
		public Object[][] get_matrix(){
			return mymatrix;
		}
		
}
	