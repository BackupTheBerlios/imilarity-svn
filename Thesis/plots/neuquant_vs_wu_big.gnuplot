set encoding iso_8859_1
set data style boxes
set size 0.7,0.7
set grid

set yrange [0:4100]
set ylabel "rekentijd in ms" 1,0

set xrange [0:7]
set xlabel "aantal kleuren" 0,-1
set noxtics
set label 1 "4" at 0.5, graph -0.05, 0 centre norotate
set label 2 "8" at 1.5, graph -0.05, 0 centre norotate
set label 3 "16" at 2.5, graph -0.05, 0 centre norotate
set label 4 "32" at 3.5, graph -0.05, 0 centre norotate
set label 5 "64" at 4.5, graph -0.05, 0 centre norotate
set label 6 "128" at 5.5, graph -0.05, 0 centre norotate
set label 7 "256" at 6.5, graph -0.05, 0 centre norotate

set term postscript enhanced eps color solid "Time-Roman" 20
set output "neuquant_vs_wu_big.eps"

set key left top
set boxwidth 0.40
plot "-" using ($1+0.35):2 title "NeuQuant" linetype 1 axes x1y1 ,\
	 "-" using ($1+0.60):2 title "Wu" linetype 2 axes x1y1

0	1078
1	1215
2	1252
3	1441
4	1796
5	2517
6	4002
e
0	1837
1	1810
2	1814
3	1818
4	1853
5	1821
6	1841
e

