set encoding iso_8859_1
set data style boxes
set size 1.0,0.7
set grid

set yrange [0.0:0.8]
set y2range [4096:120000]
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
set output "dom_colors_wu_gggrs_en_cputimes.eps"

set key left top
set boxwidth 0.15
plot "-" using ($1+0.24):2 title "rekentijd grijswaarde" linetype 5 axes x1y2 ,\
	 "-" using ($1+0.50):2 title "rekentijd componenten" linetype 6 axes x1y2,\
	 "-" using ($1+0.76):2 title "rekentijd tralie" linetype 4 axes x1y2,\
 	 "-" using ($1+0.20):3 title "GGGR grijswaarde" linetype 1 axes x1y1,\
         "-" using ($1+0.46):3 title "GGGR componenten" linetype 2 axes x1y1,\
         "-" using ($1+0.72):3 title "GGGR tralie" linetype 3 axes x1y1,\
	 "-" using ($1+0.28):3 title "" linetype 1 axes x1y1,\
         "-" using ($1+0.54):3 title "" linetype 2 axes x1y1,\
         "-" using ($1+0.80):3 title "" linetype 3 axes x1y1

0	53870	0.175974025974026
1	52965	0.18095238095238095
2	53221	0.19848484848484846
3	53367	0.2090909090909091
4	52691	0.16558441558441558
5	52312	0.09307359307359307
6	52527	0.09177489177489177
7	52084	0.16558441558441558
8	52100	0.1829004329004329
9	51631	0.10735930735930738
10	51917	0.1266233766233766
11	50980	0.3898268398268398
12	50927	0.38701298701298703
13	50751	0.09653679653679656
14	50461	0.10995670995670995
15	50215	0.4374458874458874
16	50109	0.41731601731601736
17	50336	0.09307359307359307
18	49868	0.09177489177489177
19	50451	0.1266233766233766
20	49615	0.38701298701298703
21	50515	0.17424242424242428
22	49579	0.17813852813852818
e
0	49118	0.07792207792207792
1	49171	0.08008658008658008
2	48462	0.09134199134199132
3	48500	0.10432900432900434
4	48618	0.06688311688311688
5	48582	0.018181818181818184
6	48601	0.02186147186147186
7	49398	0.0712121212121212
8	48541	0.0958874458874459
9	48524	0.03744588744588744
10	48549	0.04675324675324675
11	49298	0.4634199134199133
12	48860	0.43203463203463205
13	48306	0.026623376623376625
14	47651	0.03376623376623377
15	47747	0.5138528138528138
16	48047	0.4692640692640693
17	48255	0.018181818181818184
18	48022	0.02186147186147186
19	48254	0.04675324675324675
20	48211	0.43203463203463205
21	48186	0.07922077922077922
22	48225	0.08658008658008658
e
0	47875	0.05974025974025974
1	47919	0.07965367965367966
2	47800	0.11991341991341989
3	48110	0.16645021645021643
4	47964	0.05735930735930736
5	47919	0.10432900432900434
6	47929	0.10692640692640691
7	47989	0.17683982683982677
8	47878	0.18852813852813852
9	48024	0.11471861471861472
10	48030	0.13484848484848488
11	47797	0.38896103896103895
12	47990	0.38896103896103895
13	48000	0.10324675324675324
14	48148	0.11493506493506493
15	48069	0.43225108225108233
16	47985	0.41991341991341996
17	48020	0.10432900432900434
18	48245	0.10692640692640691
19	47901	0.13484848484848488
20	48305	0.38896103896103895
21	48086	0.1538961038961039
22	48450	0.17272727272727273
e
0	53870	0.175974025974026
1	52965	0.18095238095238095
2	53221	0.19848484848484846
3	53367	0.2090909090909091
4	52691	0.16558441558441558
5	52312	0.09307359307359307
6	52527	0.09177489177489177
7	52084	0.16558441558441558
8	52100	0.1829004329004329
9	51631	0.10735930735930738
10	51917	0.1266233766233766
11	50980	0.3898268398268398
12	50927	0.38701298701298703
13	50751	0.09653679653679656
14	50461	0.10995670995670995
15	50215	0.4374458874458874
16	50109	0.41731601731601736
17	50336	0.09307359307359307
18	49868	0.09177489177489177
19	50451	0.1266233766233766
20	49615	0.38701298701298703
21	50515	0.17424242424242428
22	49579	0.17813852813852818
e
0	49118	0.07792207792207792
1	49171	0.08008658008658008
2	48462	0.09134199134199132
3	48500	0.10432900432900434
4	48618	0.06688311688311688
5	48582	0.018181818181818184
6	48601	0.02186147186147186
7	49398	0.0712121212121212
8	48541	0.0958874458874459
9	48524	0.03744588744588744
10	48549	0.04675324675324675
11	49298	0.4634199134199133
12	48860	0.43203463203463205
13	48306	0.026623376623376625
14	47651	0.03376623376623377
15	47747	0.5138528138528138
16	48047	0.4692640692640693
17	48255	0.018181818181818184
18	48022	0.02186147186147186
19	48254	0.04675324675324675
20	48211	0.43203463203463205
21	48186	0.07922077922077922
22	48225	0.08658008658008658
e
0	47875	0.05974025974025974
1	47919	0.07965367965367966
2	47800	0.11991341991341989
3	48110	0.16645021645021643
4	47964	0.05735930735930736
5	47919	0.10432900432900434
6	47929	0.10692640692640691
7	47989	0.17683982683982677
8	47878	0.18852813852813852
9	48024	0.11471861471861472
10	48030	0.13484848484848488
11	47797	0.38896103896103895
12	47990	0.38896103896103895
13	48000	0.10324675324675324
14	48148	0.11493506493506493
15	48069	0.43225108225108233
16	47985	0.41991341991341996
17	48020	0.10432900432900434
18	48245	0.10692640692640691
19	47901	0.13484848484848488
20	48305	0.38896103896103895
21	48086	0.1538961038961039
22	48450	0.17272727272727273
e
0	53870	0.175974025974026
1	52965	0.18095238095238095
2	53221	0.19848484848484846
3	53367	0.2090909090909091
4	52691	0.16558441558441558
5	52312	0.09307359307359307
6	52527	0.09177489177489177
7	52084	0.16558441558441558
8	52100	0.1829004329004329
9	51631	0.10735930735930738
10	51917	0.1266233766233766
11	50980	0.3898268398268398
12	50927	0.38701298701298703
13	50751	0.09653679653679656
14	50461	0.10995670995670995
15	50215	0.4374458874458874
16	50109	0.41731601731601736
17	50336	0.09307359307359307
18	49868	0.09177489177489177
19	50451	0.1266233766233766
20	49615	0.38701298701298703
21	50515	0.17424242424242428
22	49579	0.17813852813852818
e
0	49118	0.07792207792207792
1	49171	0.08008658008658008
2	48462	0.09134199134199132
3	48500	0.10432900432900434
4	48618	0.06688311688311688
5	48582	0.018181818181818184
6	48601	0.02186147186147186
7	49398	0.0712121212121212
8	48541	0.0958874458874459
9	48524	0.03744588744588744
10	48549	0.04675324675324675
11	49298	0.4634199134199133
12	48860	0.43203463203463205
13	48306	0.026623376623376625
14	47651	0.03376623376623377
15	47747	0.5138528138528138
16	48047	0.4692640692640693
17	48255	0.018181818181818184
18	48022	0.02186147186147186
19	48254	0.04675324675324675
20	48211	0.43203463203463205
21	48186	0.07922077922077922
22	48225	0.08658008658008658
e
0	47875	0.05974025974025974
1	47919	0.07965367965367966
2	47800	0.11991341991341989
3	48110	0.16645021645021643
4	47964	0.05735930735930736
5	47919	0.10432900432900434
6	47929	0.10692640692640691
7	47989	0.17683982683982677
8	47878	0.18852813852813852
9	48024	0.11471861471861472
10	48030	0.13484848484848488
11	47797	0.38896103896103895
12	47990	0.38896103896103895
13	48000	0.10324675324675324
14	48148	0.11493506493506493
15	48069	0.43225108225108233
16	47985	0.41991341991341996
17	48020	0.10432900432900434
18	48245	0.10692640692640691
19	47901	0.13484848484848488
20	48305	0.38896103896103895
21	48086	0.1538961038961039
22	48450	0.17272727272727273
e
