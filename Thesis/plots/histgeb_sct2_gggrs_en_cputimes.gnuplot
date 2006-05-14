set encoding iso_8859_1
set data style boxes
set size 1.0,0.7
set grid

set yrange [0:0.6]
set y2range [1000:1000000]
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
set label 9 "M_{6c}" at 8.5, graph -0.05, 0 centre norotat
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
set output "histgeb_sct2_gggrs_en_cputimes.eps"

set key left top
set boxwidth 0.15
plot "-" using ($1+0.24):2 title "rekentijd gladde SCT" linetype 5 axes x1y2 ,\
	 "-" using ($1+0.50):2 title "rekentijd gladde SCT centrum" linetype 6 axes x1y2,\
	 "-" using ($1+0.76):2 title "rekentijd gladde SCT rand" linetype 4 axes x1y2,\
 	 "-" using ($1+0.20):3 title "GGGR gladde SCT" linetype 1 axes x1y1,\
	 "-" using ($1+0.46):3 title "GGGR gladde SCT centrum" linetype 2 axes x1y1,\
         "-" using ($1+0.72):3 title "GGGR gladde SCT rand" linetype 3 axes x1y1,\
	 "-" using ($1+0.28):3 title "" linetype 1 axes x1y1,\
	 "-" using ($1+0.54):3 title "" linetype 2 axes x1y1,\
         "-" using ($1+0.80):3 title "" linetype 3 axes x1y1

0	36634	0.03874458874458874
1	36829	0.051948051948051945
2	37075	0.060173160173160184
3	37002	0.06818181818181818
4	37017	0.02943722943722944
5	36584	0.2103896103896104
6	36337	0.21233766233766235
7	37445	0.02943722943722944
8	37696	0.03896103896103895
9	37652	0.03744588744588744
10	37543	0.05411255411255411
11	37445	0.03614718614718615
12	37267	0.051948051948051945
13	37194	0.06731601731601732
14	37303	0.05432900432900434
15	37591	0.05411255411255411
16	37349	0.05238095238095239
17	39868	0.2103896103896104
18	39478	0.21233766233766235
19	40390	0.05411255411255411
20	40285	0.051948051948051945
21	37697	0.022727272727272724
22	37938	0.03874458874458874
e
0	42656	0.022294372294372294
1	42742	0.0367965367965368
2	42363	0.04675324675324675
3	42620	0.10324675324675324
4	43094	0.025108225108225104
5	42637	0.24696969696969695
6	42423	0.24523809523809526
7	43601	0.025108225108225104
8	43337	0.02316017316017316
9	43562	0.02965367965367965
10	43081	0.03506493506493506
11	43271	0.04004329004329004
12	43333	0.05194805194805194
13	43160	0.05909090909090908
14	42949	0.03701298701298701
15	43066	0.06038961038961039
16	43179	0.052380952380952375
17	45639	0.24696969696969695
18	45496	0.24523809523809526
19	46458	0.03506493506493506
20	46207	0.05194805194805194
21	43634	0.026839826839826834
22	43852	0.022943722943722943
e
0	132520	0.035281385281385275
1	131814	0.05714285714285714
2	131786	0.06969696969696969
3	131826	0.07467532467532469
4	132624	0.03917748917748917
5	131960	0.18463203463203462
6	131774	0.18030303030303035
7	132784	0.03917748917748917
8	132774	0.03658008658008658
9	132556	0.044805194805194806
10	132559	0.04415584415584416
11	132574	0.0645021645021645
12	132538	0.12380952380952381
13	132775	0.07575757575757575
14	132208	0.0448051948051948
15	132573	0.14155844155844155
16	132576	0.12705627705627706
17	135983	0.18463203463203462
18	135744	0.18030303030303035
19	136509	0.04415584415584416
20	136294	0.12380952380952381
21	132918	0.040259740259740266
22	133048	0.03658008658008658
e
0	36634	0.03874458874458874
1	36829	0.051948051948051945
2	37075	0.060173160173160184
3	37002	0.06818181818181818
4	37017	0.02943722943722944
5	36584	0.2103896103896104
6	36337	0.21233766233766235
7	37445	0.02943722943722944
8	37696	0.03896103896103895
9	37652	0.03744588744588744
10	37543	0.05411255411255411
11	37445	0.03614718614718615
12	37267	0.051948051948051945
13	37194	0.06731601731601732
14	37303	0.05432900432900434
15	37591	0.05411255411255411
16	37349	0.05238095238095239
17	39868	0.2103896103896104
18	39478	0.21233766233766235
19	40390	0.05411255411255411
20	40285	0.051948051948051945
21	37697	0.022727272727272724
22	37938	0.03874458874458874
e
0	42656	0.022294372294372294
1	42742	0.0367965367965368
2	42363	0.04675324675324675
3	42620	0.10324675324675324
4	43094	0.025108225108225104
5	42637	0.24696969696969695
6	42423	0.24523809523809526
7	43601	0.025108225108225104
8	43337	0.02316017316017316
9	43562	0.02965367965367965
10	43081	0.03506493506493506
11	43271	0.04004329004329004
12	43333	0.05194805194805194
13	43160	0.05909090909090908
14	42949	0.03701298701298701
15	43066	0.06038961038961039
16	43179	0.052380952380952375
17	45639	0.24696969696969695
18	45496	0.24523809523809526
19	46458	0.03506493506493506
20	46207	0.05194805194805194
21	43634	0.026839826839826834
22	43852	0.022943722943722943
e
0	132520	0.035281385281385275
1	131814	0.05714285714285714
2	131786	0.06969696969696969
3	131826	0.07467532467532469
4	132624	0.03917748917748917
5	131960	0.18463203463203462
6	131774	0.18030303030303035
7	132784	0.03917748917748917
8	132774	0.03658008658008658
9	132556	0.044805194805194806
10	132559	0.04415584415584416
11	132574	0.0645021645021645
12	132538	0.12380952380952381
13	132775	0.07575757575757575
14	132208	0.0448051948051948
15	132573	0.14155844155844155
16	132576	0.12705627705627706
17	135983	0.18463203463203462
18	135744	0.18030303030303035
19	136509	0.04415584415584416
20	136294	0.12380952380952381
21	132918	0.040259740259740266
22	133048	0.03658008658008658
e
0	36634	0.03874458874458874
1	36829	0.051948051948051945
2	37075	0.060173160173160184
3	37002	0.06818181818181818
4	37017	0.02943722943722944
5	36584	0.2103896103896104
6	36337	0.21233766233766235
7	37445	0.02943722943722944
8	37696	0.03896103896103895
9	37652	0.03744588744588744
10	37543	0.05411255411255411
11	37445	0.03614718614718615
12	37267	0.051948051948051945
13	37194	0.06731601731601732
14	37303	0.05432900432900434
15	37591	0.05411255411255411
16	37349	0.05238095238095239
17	39868	0.2103896103896104
18	39478	0.21233766233766235
19	40390	0.05411255411255411
20	40285	0.051948051948051945
21	37697	0.022727272727272724
22	37938	0.03874458874458874
e
0	42656	0.022294372294372294
1	42742	0.0367965367965368
2	42363	0.04675324675324675
3	42620	0.10324675324675324
4	43094	0.025108225108225104
5	42637	0.24696969696969695
6	42423	0.24523809523809526
7	43601	0.025108225108225104
8	43337	0.02316017316017316
9	43562	0.02965367965367965
10	43081	0.03506493506493506
11	43271	0.04004329004329004
12	43333	0.05194805194805194
13	43160	0.05909090909090908
14	42949	0.03701298701298701
15	43066	0.06038961038961039
16	43179	0.052380952380952375
17	45639	0.24696969696969695
18	45496	0.24523809523809526
19	46458	0.03506493506493506
20	46207	0.05194805194805194
21	43634	0.026839826839826834
22	43852	0.022943722943722943
e
0	132520	0.035281385281385275
1	131814	0.05714285714285714
2	131786	0.06969696969696969
3	131826	0.07467532467532469
4	132624	0.03917748917748917
5	131960	0.18463203463203462
6	131774	0.18030303030303035
7	132784	0.03917748917748917
8	132774	0.03658008658008658
9	132556	0.044805194805194806
10	132559	0.04415584415584416
11	132574	0.0645021645021645
12	132538	0.12380952380952381
13	132775	0.07575757575757575
14	132208	0.0448051948051948
15	132573	0.14155844155844155
16	132576	0.12705627705627706
17	135983	0.18463203463203462
18	135744	0.18030303030303035
19	136509	0.04415584415584416
20	136294	0.12380952380952381
21	132918	0.040259740259740266
22	133048	0.03658008658008658
e
