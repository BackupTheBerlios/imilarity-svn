set encoding iso_8859_1
set data style boxes
set size 1.0,0.7
set grid

set yrange [0.0:0.4]
set y2range [4096:60000]
set log y2 2
set format y2 "2^{%L}"
set y2tics

set ylabel "GGGR" 1,0
set y2label "rekentijd in ms" -2,0

set xrange [0:23]
set noxtics
set label 1 "M_{1a}" at 0.5, graph -0.05, 0 centre norotate
set label 2 "M_{1b}" at 1.5, graph -0.05, 0 centre norotate
set label 3 "M_{1c}" at 2.5, graph -0.05, 0 centre norotate
set label 4 "M_2" at 3.5, graph -0.05, 0 centre norotate
set label 5 "M_3" at 4.5, graph -0.05, 0 centre norotate
set label 6 "M_5" at 5.5, graph -0.05, 0 centre norotate
set label 7 "M_{5c}" at 6.5, graph -0.05, 0 centre norotate
set label 8 "M_6" at 7.5, graph -0.05, 0 centre norotate
set label 9 "M_{6c}" at 8.5, graph -0.05, 0 centre norotate
set label 10 "M_7" at 9.5, graph -0.05, 0 centre norotate
set label 11 "M_{7c}" at 10.5, graph -0.05, 0 centre norotate
set label 12 "M_8" at 11.5, graph -0.05, 0 centre norotate
set label 13 "M_{8c}" at 12.5, graph -0.05, 0 centre norotate
set label 14 "M_9" at 13.5, graph -0.05, 0 centre norotate
set label 15 "M_{9c}" at 14.5, graph -0.05, 0 centre norotate
set label 16 "M_{10}" at 15.5, graph -0.05, 0 centre norotate
set label 17 "M_{10c}" at 16.5, graph -0.05, 0 centre norotate
set label 18 "M_{11}" at 17.5, graph -0.05, 0 centre norotate
set label 19 "M_{11c}" at 18.5, graph -0.05, 0 centre norotate
set label 20 "M_{12}" at 19.5, graph -0.05, 0 centre norotate
set label 21 "M_{13}" at 20.5, graph -0.05, 0 centre norotate
set label 22 "M_{I_3}" at 21.5, graph -0.05, 0 centre norotate
set label 23 "M_{I_{3c}}" at 22.5, graph -0.05, 0 centre norotate

set term postscript enhanced eps color solid "Time-Roman" 12
set output "moments_comps_gggrs_en_cputimes.eps"

set key left top
set boxwidth 0.25
plot "-" using ($1+0.30):2 title "rekentijd gemiddelde" linetype 2 axes x1y2 ,\
	 "-" using ($1+0.50):2 title "rekentijd minimum" linetype 3 axes x1y2,\
	 "-" using ($1+0.70):2 title "rekentijd product" linetype 4 axes x1y2,\
 	 "-" using ($1+0.30):3 title "GGGR gemiddelde" linetype 5 axes x1y1,\
         "-" using ($1+0.50):3 title "GGGR minimum" linetype 6 axes x1y1,\
         "-" using ($1+0.70):3 title "GGGR product" linetype 7 axes x1y1

0	30551	0.015584415584415584
1	29845	0.02683982683982684
2	29817	0.03225108225108225
3	29760	0.0354978354978355
4	29809	0.009307359307359308
5	29744	0.012770562770562773
6	29783	0.027922077922077918
7	29984	0.010173160173160174
8	29809	0.021212121212121213
9	29805	0.008874458874458875
10	29909	0.02034632034632035
11	29838	0.2911255411255411
12	29833	0.27943722943722943
13	29815	0.008441558441558441
14	29925	0.020129870129870133
15	29873	0.29285714285714287
16	29857	0.2826839826839827
17	29905	0.012770562770562773
18	29927	0.027922077922077918
19	30220	0.02034632034632035
20	30257	0.2809523809523809
21	30248	0.010173160173160174
22	30269	0.021212121212121213
e
0	30098	0.016017316017316017
1	30115	0.030086580086580092
2	30138	0.04004329004329004
3	30093	0.049134199134199134
4	30161	0.006277056277056278
5	30143	0.00735930735930736
6	30284	0.02012987012987013
7	30312	0.006277056277056278
8	30180	0.02813852813852814
9	30165	0.006277056277056278
10	30316	0.02251082251082251
11	29768	0.2683982683982684
12	29731	0.26277056277056277
13	29729	0.006493506493506493
14	29719	0.02207792207792208
15	29730	0.2748917748917749
16	29743	0.2616883116883117
17	29847	0.00735930735930736
18	29764	0.02012987012987013
19	29808	0.02251082251082251
20	29819	0.2632034632034632
21	29950	0.006277056277056278
22	29813	0.02813852813852814
e
0	29693	0.0158008658008658
1	29725	0.02640692640692641
2	29740	0.032251082251082246
3	29717	0.03593073593073593
4	29769	0.008874458874458873
5	29758	0.011471861471861473
6	29750	0.027922077922077918
7	29762	0.009307359307359308
8	29770	0.021645021645021648
9	29719	0.008441558441558443
10	29749	0.020562770562770564
11	29759	0.29069264069264067
12	29875	0.2785714285714285
13	29781	0.008225108225108226
14	29808	0.02034632034632035
15	29798	0.2924242424242424
16	29763	0.28225108225108225
17	29838	0.011471861471861473
18	29832	0.027922077922077918
19	30045	0.020562770562770564
20	29908	0.28008658008658005
21	29885	0.009307359307359308
22	29847	0.021645021645021648
e
0	30551	0.015584415584415584
1	29845	0.02683982683982684
2	29817	0.03225108225108225
3	29760	0.0354978354978355
4	29809	0.009307359307359308
5	29744	0.012770562770562773
6	29783	0.027922077922077918
7	29984	0.010173160173160174
8	29809	0.021212121212121213
9	29805	0.008874458874458875
10	29909	0.02034632034632035
11	29838	0.2911255411255411
12	29833	0.27943722943722943
13	29815	0.008441558441558441
14	29925	0.020129870129870133
15	29873	0.29285714285714287
16	29857	0.2826839826839827
17	29905	0.012770562770562773
18	29927	0.027922077922077918
19	30220	0.02034632034632035
20	30257	0.2809523809523809
21	30248	0.010173160173160174
22	30269	0.021212121212121213
e
0	30098	0.016017316017316017
1	30115	0.030086580086580092
2	30138	0.04004329004329004
3	30093	0.049134199134199134
4	30161	0.006277056277056278
5	30143	0.00735930735930736
6	30284	0.02012987012987013
7	30312	0.006277056277056278
8	30180	0.02813852813852814
9	30165	0.006277056277056278
10	30316	0.02251082251082251
11	29768	0.2683982683982684
12	29731	0.26277056277056277
13	29729	0.006493506493506493
14	29719	0.02207792207792208
15	29730	0.2748917748917749
16	29743	0.2616883116883117
17	29847	0.00735930735930736
18	29764	0.02012987012987013
19	29808	0.02251082251082251
20	29819	0.2632034632034632
21	29950	0.006277056277056278
22	29813	0.02813852813852814
e
0	29693	0.0158008658008658
1	29725	0.02640692640692641
2	29740	0.032251082251082246
3	29717	0.03593073593073593
4	29769	0.008874458874458873
5	29758	0.011471861471861473
6	29750	0.027922077922077918
7	29762	0.009307359307359308
8	29770	0.021645021645021648
9	29719	0.008441558441558443
10	29749	0.020562770562770564
11	29759	0.29069264069264067
12	29875	0.2785714285714285
13	29781	0.008225108225108226
14	29808	0.02034632034632035
15	29798	0.2924242424242424
16	29763	0.28225108225108225
17	29838	0.011471861471861473
18	29832	0.027922077922077918
19	30045	0.020562770562770564
20	29908	0.28008658008658005
21	29885	0.009307359307359308
22	29847	0.021645021645021648
e
