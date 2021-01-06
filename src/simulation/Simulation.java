package QTL;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static QTL.FindPos.readRange;

public class Simulation {
    public static void main(String[] args) throws IOException {
        Fasta ft = new Fasta();
//        ft.setBlockss("H:/Nature/2020PreExperiment/JNW/src/QTL/subTrans.fasta");
        ft.setBlocks("H:/Nature/2020PreExperiment/JNW/src/QTL/IWGSC_v1.1_HC_20170706_transcripts.fasta");
        ArrayList<ArrayList<String>> tt = ft.getBlock();
        System.out.println(tt.get(2));
        ft.filter();
        ArrayList<Integer> startPos = Fasta.getRandomStartPos(ft.getFilterBlock());
        ArrayList<String> random350 = Fasta.randomSequence(ft.getFilterBlock(), 0);

        int[] slice = Fasta.randomCommon(0,ft.getFilterBlock().size(),10000);
        ArrayList<String> resName = new ArrayList<>();
        ArrayList<String> resSequence = new ArrayList<>();
        System.out.println(Arrays.toString(slice));

        for (int num:slice) {
            resName.add(ft.getFilterGeneName().get(num));
            String temp = Fasta.random(random350.get(num));
            resSequence.add(temp);
        }

        ArrayList<String> resForwardSequence = new ArrayList<>();
        ArrayList<String> resBackSequence = new ArrayList<>();
        for (String seq : resSequence) {
            String forward = (String) seq.subSequence(0, 150);
            resForwardSequence.add(forward);
            StringBuilder sb = new StringBuilder();
            for (int i =  seq.length()-1; i > (seq.length()-151); i--) {
                if (seq.charAt(i) == 'A') {
                    sb.append('T');
                }else if (seq.charAt(i) == 'T'){
                    sb.append('A');
                }else if (seq.charAt(i) == 'C'){
                    sb.append('G');
                } else if (seq.charAt(i) == 'G'){
                    sb.append('C');}
//                sb.append(seq.charAt(i));
            }
            resBackSequence.add(sb.toString());
        }


        ArrayList<ArrayList<Integer>> geneRange = new ArrayList<>();
        for (ArrayList<String> block: ft.getFilterBlock()) {
            ArrayList<Integer> res2 = readRange(block.get(0));
            geneRange.add(res2);
        }
        BufferedWriter br3 = new BufferedWriter(new FileWriter("H:/Nature/2020PreExperiment/JNW/src/QTL/information_2.txt"));
        br3.write("slice" + "\t" + "startPhyPos" + "\t" + "endPhyPos" + "\t" + "Chro");
        br3.newLine();
        int ii= 0;
        for (int num:slice) {
            br3.write(num + "\t" + geneRange.get(num).get(startPos.get(num)) + "\t" + geneRange.get(num).get(startPos.get(num) + 350) + "\t" + resName.get(ii).split(" ")[0]);
            br3.newLine();
            ii++;
//            geneRange.get(num).get(startPos.get(num));
        }
        br3.flush();
        br3.close();
//        System.out.println(random350.get(slice[0]));
//        System.out.println(resForwardSequence.get(0));
//        System.out.println(random350.get(slice[2]));
//        System.out.println(resForwardSequence.get(2));
//        System.out.println(random350.get(slice[3]));
//        System.out.println(resForwardSequence.get(3));
//        System.out.println(random350.get(4));
//        System.out.println(resForwardSequence.get(4));
        BufferedWriter br = new BufferedWriter(new FileWriter("H:/Nature/2020PreExperiment/JNW/src/QTL/front_2.txt"));
        for (int i = 0; i < resForwardSequence.size(); i++) {
            br.write(resForwardSequence.get(i));
            br.newLine();
//            System.out.println(resForwardSequence.get(i));
        }
        br.flush();
        br.close();

        BufferedWriter br1 = new BufferedWriter(new FileWriter("H:/Nature/2020PreExperiment/JNW/src/QTL/back_2.txt"));
        for (int i = 0; i < resBackSequence.size(); i++) {
            br1.write(resBackSequence.get(i));
            br1.newLine();
//            System.out.println(resForwardSequence.get(i));
        }
        br1.flush();
        br1.close();

//        BufferedWriter br2 = new BufferedWriter(new FileWriter("H:/Nature/2020PreExperiment/JNW/src/QTL/information.txt"));
//        br2.write(String.valueOf(slice));
//        br2.newLine();
//        br2.write("below is start postion");
//        br2.write(String.valueOf(startPos));
//        br2.flush();
//        br2.close();


//        System.out.println(ft.getFilterGeneName().size());
//        System.out.println(ft.getFilterBlock().size());
//        System.out.println(ft.getFilterBlock().size());
//        System.out.println(ft.getShortBlock().size());

//        ft.subSequence(150);
//        System.out.println(ft.backSequence.get(2));

//        ArrayList<String> forwardSequence = ft.getForwardSequence();
//        System.out.println(forwardSequence.get(5));

//        ArrayList<String> testForwardSequence = Fasta.randomVariation(forwardSequence);
//        System.out.println(forwardSequence.get(5));
//        System.out.println(ft.getForwardSequence().get(5));
//        System.out.println(testForwardSequence.get(5));
//        System.out.println(testForwardSequence.get(5).equals(forwardSequence.get(5)));
    }
}
