import java.util.Arrays;
import java.util.Comparator;
public class IntervalSchedulingNonWeighted {


    static class Job
    {
        int start, finish,conflict;

        Job(int start, int finish)
        {
            this.start = start;
            this.finish = finish;
            this.conflict=conflict;
        }
    }


    //sort according to finish times
    static class JobComparator implements Comparator<Job>
    {
        public int compare(Job a, Job b)
        {
            return Integer.compare(a.finish, b.finish);
        }
    }

    class IntervalScheduling
    {

        static public Job[] schedule(Job jobs[])
        {
            //sort n bookings by finish time
            Arrays.sort(jobs, new JobComparator());

            int n = jobs.length;
            Job[] table=new Job[n];

            table[0]=jobs[0];
            int k=0;
                for (int m = 1; m < n; m++) {
                    if (jobs[m].start >= table[k].finish) {
                        table[++k] = jobs[m];
                    }
                }
            return table;
        }

        static public void conflicts(Job jobs[]){
            for(int i=0;i< jobs.length/2;i++){
                for(int j=jobs.length-1;j>=0;j--){
                    if(j<=i){
                        break;
                    }
                    if(jobs[j].start<jobs[i].finish && jobs[i].start<jobs[j].finish){
                        jobs[j].conflict++;
                        jobs[i].conflict++;
                    }
                }
            }
        }

        static public Job minimumConflicts(Job jobs[]){
            Job minimumConflicts=jobs[0];
            minimumConflicts.conflict = Integer.MAX_VALUE;
            for(int i=0;i<jobs.length;i++){
                if(jobs[i].conflict<minimumConflicts.conflict){
                    minimumConflicts=jobs[i];
                }
            }
            return minimumConflicts;
        }

        static public Job maximumConflicts(Job jobs[]){
            Job maximumConflicts=jobs[0];
            maximumConflicts.conflict = Integer.MIN_VALUE;
            for(int i=1;i<jobs.length;i++){
                if(jobs[i].conflict>maximumConflicts.conflict){
                    maximumConflicts=jobs[i];
                }
            }
            return maximumConflicts;
        }


    }
    public static void main(String[] args)
    {
        Job[] jobs = {new Job(2, 4), new Job(3, 4), new Job(1, 3), new Job(4, 5),new Job(3, 5),new Job(2, 5),new Job(6, 8),new Job(7, 9),new Job(8, 9),new Job(9, 10)};
        Job[] table= IntervalScheduling.schedule(jobs);
        IntervalScheduling.conflicts(jobs);

        for(int i=0;i< table.length;i++){
            if(table[i]==null)
                break;
            System.out.println("("+table[i].start+","+table[i].finish+","+table[i].conflict+")");
        }
        System.out.println("Job with minimum number of conflicts: "+"("+IntervalScheduling.minimumConflicts(jobs).start+","+IntervalScheduling.minimumConflicts(jobs).finish+")"+" number of conflicts: "+IntervalScheduling.minimumConflicts(jobs).conflict);
        System.out.println("Job with maximum number of conflicts: "+"("+IntervalScheduling.maximumConflicts(jobs).start+","+IntervalScheduling.maximumConflicts(jobs).finish+")"+" number of conflicts: "+IntervalScheduling.maximumConflicts(jobs).conflict);


    }
}
