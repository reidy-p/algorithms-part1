import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {

    private double[] frac_open_sites;
    private int trials, n;
    private double sample_mean, sample_std;
    
    public PercolationStats(int n, int trials) {   // perform trials independent experiments on an n-by-n grid
        
        this.n = n;
        this.trials = trials;
        frac_open_sites = new double[trials];
        
        for (int i = 0; i < trials; i++) {
  
            Percolation p = new Percolation(n);
            double count = 0.0;
            while (!p.percolates()) {
                
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                
                if (!p.isOpen(row, col)) {
                    
                    p.open(row, col);
                    count++;
                    
                }    
                
            }

        frac_open_sites[i] = count / (n*n);
        
        }
        
    }

   public double mean() {                         // sample mean of percolation threshold
     
     sample_mean = StdStats.mean(frac_open_sites); 
     return(sample_mean);
       
   }    

   public double stddev() {                         // sample standard deviation of percolation threshold
     
     sample_std = StdStats.stddev(frac_open_sites);
     return(sample_std);
       
   } 
   
   public double confidenceLo() {                  // low  endpoint of 95% confidence interval
   
       return(sample_mean - 1.96*sample_std/Math.sqrt(trials));
       
   }

    public double confidenceHi() {                  // high  endpoint of 95% confidence interval
   
       return(sample_mean + 1.96*sample_std/Math.sqrt(trials));
       
   }

   public static void main(String[] args) {       // test client (described below)
      
      int n = Integer.parseInt(args[0]);
      int trials = Integer.parseInt(args[1]);
      PercolationStats s = new PercolationStats(n, trials);
      System.out.println(s.mean());
      System.out.println(s.stddev());
      System.out.println(s.confidenceLo());
      System.out.println(s.confidenceHi());
      
   }        
 }
