#!/bin/sh

for i in *.gnuplot
do
	b=`basename $i .gnuplot`
	gnuplot $i
	./gnuplot-boxfill.pl -c $b.eps ${b}_filled.eps
	cat ${b}_filled.eps | sed \
	-e 's/\(\/LT4.*\[.*\]\) . . ./\1 0.1 1 0.8/' \
	-e 's/\(\/LT5.*\[.*\]\) . . ./\0.8 0.55 0.7/' \
	-e 's/\(\/LT3.*\[.*\]\) . . ./\1 1 0.5 0/' > ${b}_filled.tmp
	mv ${b}_filled.tmp ${b}_filled.eps
done
