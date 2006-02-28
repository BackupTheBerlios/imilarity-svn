set encoding iso_8859_1
set data style boxes
set size 1.0,0.7
set grid

set yrange [0.0:0.5]
set y2range [4096:70000]
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
set label 22 "M_{I3}" at 21.5, graph -0.05, 0 centre norotate
set label 23 "M_{I3c}" at 22.5, graph -0.05, 0 centre norotate

set term postscript enhanced eps color solid "Time-Roman" 12
set output "dom_colors_neuquant_gggrs_en_cputimes.eps"

set key left top
set boxwidth 0.25
plot "-" using ($1+0.30):2 title "rekentijd grijswaarde" linetype 2 axes x1y2 ,\
	 "-" using ($1+0.50):2 title "rekentijd componenten" linetype 3 axes x1y2,\
	 "-" using ($1+0.70):2 title "rekentijd tralie" linetype 4 axes x1y2,\
 	 "-" using ($1+0.30):3 title "GGGR grijswaarde" linetype 5 axes x1y1,\
         "-" using ($1+0.50):3 title "GGGR componenten" linetype 6 axes x1y1,\
         "-" using ($1+0.70):3 title "GGGR tralie" linetype 7 axes x1y1

0	32186	0.15649350649350646
1	31641	0.18225108225108225
2	31550	0.1937229437229437
3	31484	0.21883116883116882
4	31550	0.16709956709956708
5	31660	0.1824675324675325
6	37074	0.1865800865800866
7	31578	0.16709956709956708
8	31520	0.15432900432900432
9	31996	0.15281385281385282
10	31516	0.14242424242424243
11	31428	0.20584415584415588
12	31552	0.2008658008658009
13	31431	0.13917748917748918
14	31431	0.1393939393939394
15	31418	0.2212121212121212
16	31475	0.21060606060606063
17	31464	0.1824675324675325
18	31486	0.1865800865800866
19	31544	0.14242424242424243
20	31536	0.20108225108225108
21	31492	0.16796536796536798
22	31506	0.1545454545454545
e
0	31486	0.137012987012987
1	31465	0.17012987012987013
2	31478	0.18268398268398267
3	31560	0.2004329004329004
4	31515	0.15476190476190477
5	31446	0.07251082251082251
6	31510	0.08225108225108224
7	31580	0.15562770562770561
8	31563	0.13787878787878788
9	31578	0.1305194805194805
10	31597	0.1103896103896104
11	31548	0.20238095238095238
12	31551	0.19848484848484851
13	31563	0.09783549783549783
14	32278	0.1012987012987013
15	31539	0.23246753246753246
16	31521	0.21645021645021645
17	31633	0.07251082251082251
18	31577	0.08225108225108224
19	31737	0.1103896103896104
20	32633	0.19848484848484851
21	31737	0.16038961038961042
22	31714	0.13658008658008658
e
0	31427	0.1316017316017316
1	31430	0.1699134199134199
2	31455	0.18809523809523807
3	31738	0.21190476190476187
4	32108	0.14653679653679655
5	31393	0.1655844155844156
6	31467	0.16904761904761906
7	31511	0.16818181818181818
8	31480	0.1538961038961039
9	31732	0.1512987012987013
10	31484	0.13636363636363635
11	31427	0.2062770562770563
12	31467	0.20194805194805196
13	31461	0.1303030303030303
14	31466	0.1329004329004329
15	31516	0.22554112554112551
16	31534	0.21948051948051947
17	31541	0.1655844155844156
18	31499	0.16904761904761906
19	31575	0.13636363636363635
20	31570	0.20216450216450219
21	31582	0.16255411255411253
22	31614	0.15173160173160172
e
0	32186	0.15649350649350646
1	31641	0.18225108225108225
2	31550	0.1937229437229437
3	31484	0.21883116883116882
4	31550	0.16709956709956708
5	31660	0.1824675324675325
6	37074	0.1865800865800866
7	31578	0.16709956709956708
8	31520	0.15432900432900432
9	31996	0.15281385281385282
10	31516	0.14242424242424243
11	31428	0.20584415584415588
12	31552	0.2008658008658009
13	31431	0.13917748917748918
14	31431	0.1393939393939394
15	31418	0.2212121212121212
16	31475	0.21060606060606063
17	31464	0.1824675324675325
18	31486	0.1865800865800866
19	31544	0.14242424242424243
20	31536	0.20108225108225108
21	31492	0.16796536796536798
22	31506	0.1545454545454545
e
0	31486	0.137012987012987
1	31465	0.17012987012987013
2	31478	0.18268398268398267
3	31560	0.2004329004329004
4	31515	0.15476190476190477
5	31446	0.07251082251082251
6	31510	0.08225108225108224
7	31580	0.15562770562770561
8	31563	0.13787878787878788
9	31578	0.1305194805194805
10	31597	0.1103896103896104
11	31548	0.20238095238095238
12	31551	0.19848484848484851
13	31563	0.09783549783549783
14	32278	0.1012987012987013
15	31539	0.23246753246753246
16	31521	0.21645021645021645
17	31633	0.07251082251082251
18	31577	0.08225108225108224
19	31737	0.1103896103896104
20	32633	0.19848484848484851
21	31737	0.16038961038961042
22	31714	0.13658008658008658
e
0	31427	0.1316017316017316
1	31430	0.1699134199134199
2	31455	0.18809523809523807
3	31738	0.21190476190476187
4	32108	0.14653679653679655
5	31393	0.1655844155844156
6	31467	0.16904761904761906
7	31511	0.16818181818181818
8	31480	0.1538961038961039
9	31732	0.1512987012987013
10	31484	0.13636363636363635
11	31427	0.2062770562770563
12	31467	0.20194805194805196
13	31461	0.1303030303030303
14	31466	0.1329004329004329
15	31516	0.22554112554112551
16	31534	0.21948051948051947
17	31541	0.1655844155844156
18	31499	0.16904761904761906
19	31575	0.13636363636363635
20	31570	0.20216450216450219
21	31582	0.16255411255411253
22	31614	0.15173160173160172
e
