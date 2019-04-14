package other;

import weka.clusterers.EM;
import weka.core.Instances;
import weka.core.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GMMtest {
    public static void main(String[] args) {
        new GMMtest().run();
    }

    private void run() {
        try {
            String path = "/home/schong/schong/Data/newCodigoARFF/";
            File fl = new File(path);
            File[] flList = fl.listFiles();
            List<String> arffFiles = new ArrayList<>();
            for (File codigof : flList) {
                String fname = codigof.getName();
                arffFiles.add(path + fname);
            }

            /*Instances dataset1 =  // from somewhere
            Instances dataset2 = ... // from somewhere
            // build clusterer
            EM clusterer = new EM();
            clusterer.buildClusterer(dataset1);
            // output predictions
            System.out.println("# - cluster - distribution");
            for (int i = 0; i < dataset2.numInstances(); i++) {
                int cluster = clusterer.clusterInstance(dataset2.instance(i));
                double[] dist = clusterer.distributionForInstance(dataset2.instance(i));
                System.out.print((i+1));
                System.out.print(" - ");
                System.out.print(cluster);
                System.out.print(" - ");
                System.out.print(Utils.arrayToString(dist));
                System.out.println();
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


