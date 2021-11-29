# Bioinformatics Seq Alignment
**Purpose**: This repository is meant for version control and inter-computer cloud access for a global sequence alignment program. This program uses a modified Needleman-Wuncsh algorithm with affine gap penalties to imitate a naturally occurring sequence. 

##  Configurations and Dependencies
Requires JDK 11.0.3 or higher ([Java Downloads](https://www.oracle.com/java/technologies/downloads/)).

## Installation 
1. Download the [source files](https://github.com/JJAWESOM234/bioinformatics-seq-alignment/tree/main/GlobalSequenceAlignment/src/com/globalsequencealignment) to a preferred folder.

## Operating Instructions 
1. Open your operating systems command prompt (terminal) and navigate to the directory with downloaded source code
2. Use the command `javac *.java` to compile the application
3. Use the command `java main` to run the program in the console

## Sample Input/Output
These samples are segments of nucelotides from the CHEK2 gene in humans and chimpanzees using [NCBI's database](https://www.ncbi.nlm.nih.gov/gene/?term=CHEK2).  
These samples were tested using an interactive tool with configurable scoring through [UNI Freiburg's Web Application](http://rna.informatik.uni-freiburg.de/Teaching/index.jsp?toolName=Gotoh).  


**Sample Input (1)**: 
```
Enter the first DNA sequence: ACTCTGCTGGCTGAGGCTGC
Enter the second DNA sequence: GTTTTGATTGGCTGAGGGTG
```
**Expected Output (1)**: 
```
              G    T    T    T    T    G    A    T    T    G    G    C    T    G    A    G    G    G    T    G
         0  -11  -12  -13  -14  -15  -16  -17  -18  -19  -20  -21  -22  -23  -24  -25  -26  -27  -28  -29  -30
    A  -11   -4  -13  -14  -15  -16  -17  -11  -19  -20  -21  -22  -23  -24  -25  -19  -27  -28  -29  -30  -31
    C  -12  -13   -8  -15  -16  -17  -18  -19  -15  -21  -22  -23  -17  -25  -26  -27  -23  -29  -30  -31  -32
    T  -13  -14   -8   -3  -10  -11  -16  -17  -14  -10  -20  -21  -22  -12  -23  -24  -25  -26  -27  -25  -29
    C  -14  -15  -16  -12   -7  -14  -15  -20  -21  -18  -14  -24  -16  -23  -16  -27  -28  -29  -30  -31  -29
    T  -15  -16  -10  -11   -7   -2  -13  -14  -15  -16  -17  -18  -19  -11  -21  -20  -23  -24  -25  -25  -27
    G  -16  -10  -18  -14  -15  -11    3   -8   -9  -10  -11  -12  -13  -14   -6  -16  -15  -18  -19  -20  -20
    C  -17  -18  -14  -17  -18  -14   -8   -1  -12  -13  -14  -15   -7  -17  -17  -10  -20  -19  -22  -23  -24
    T  -18  -19  -13   -9  -12  -13   -9  -12    4   -7   -8   -9  -10   -2  -12  -13  -14  -15  -16  -17  -18
    G  -19  -13  -21  -17  -13  -16   -8  -13   -7    0   -2   -3  -13  -13    3   -8   -8   -9  -10  -12  -12
    G  -20  -14  -17  -20  -21  -17  -11  -12   -8  -11    5    3   -7   -8   -8   -1   -3   -3   -4  -14   -7
    C  -21  -22  -18  -21  -23  -18  -12  -15   -9  -12   -6    1    8   -3   -4   -5   -5   -7   -7   -8  -10
    T  -22  -23  -17  -13  -16  -18  -13  -16  -10   -4   -7   -9   -3   13    2    1    0   -1   -2   -2   -4
    G  -23  -17  -25  -21  -17  -20  -13  -17  -11  -14    1   -2   -4    2   18    7    6    5    4    3    3
    A  -24  -25  -21  -24  -25  -21  -15   -8  -12  -15   -9   -3   -5    1    7   23   12   11   10    9    8
    G  -25  -19  -27  -25  -27  -22  -16  -19  -12  -16  -10   -4   -6    0    6   12   28   17   16   15   14
    G  -26  -20  -23  -26  -28  -23  -17  -20  -14  -16  -11   -5   -7   -1    5   11   17   33   22   21   20
    C  -27  -28  -24  -27  -29  -24  -18  -21  -15  -18  -12  -14    0   -2    4   10   16   22   29   18   17
    T  -28  -29  -23  -19  -22  -24  -19  -22  -16  -10  -13  -15   -9    5    3    9   15   21   18   34   23
    G  -29  -23  -31  -27  -23  -26  -19  -23  -17  -20   -5   -8  -10   -4   10    8   14   20   26   23   39
    C  -30  -31  -27  -30  -31  -27  -21  -23  -18  -21  -15   -9   -3   -5    1    7   13   19   16   22   28

Estimated Score: 28
------ACTCTGCTGGCTGAGGCTGC
GTTTTG---A--TTGGCTGAGGGTG-
```
**Sample Input (2)**:
```
Enter the first DNA sequence: GATGGAACCTGG
Enter the second DNA sequence:  AAACCTGATGGAACCTGG
```
**Expected Output (2)**: 
```
              A    A    A    C    C    T    G    A    T    G    G    A    A    C    C    T    G    G
         0  -11  -12  -13  -14  -15  -16  -17  -18  -19  -20  -21  -22  -23  -24  -25  -26  -27  -28
    G  -11   -4  -13  -14  -15  -16  -17  -11  -19  -20  -14  -15  -23  -24  -25  -26  -27  -21  -22
    A  -12   -6    1   -8  -11  -12  -13  -14   -6  -16  -17  -18  -10  -18  -21  -22  -23  -24  -25
    T  -13  -14  -10   -3  -12  -15   -7  -17  -17   -1  -12  -13  -14  -14  -16  -17  -17  -19  -20
    G  -14  -15  -11  -14   -7  -16  -18   -2  -13  -12    4   -7   -8   -9  -10  -11  -12  -12  -14
    G  -15  -16  -12  -15  -18  -11  -19  -13   -6  -13   -7    9   -2   -3   -4   -5   -6   -7   -7
    A  -16  -10  -11   -7  -18  -19  -15  -14   -8  -10   -8   -2   14    3    2    1    0   -1   -2
    A  -17  -11   -5   -6  -11  -18  -19  -15   -9  -12   -9   -3    3   19    8    7    6    5    4
    C  -18  -19  -15   -9   -1   -6  -13  -14  -15  -13  -10   -4    2    8   24   13   12   11   10
    C  -19  -20  -16  -18   -4    4   -7   -8   -9  -10  -11   -5    1    7   13   29   18   17   16
    T  -20  -21  -17  -19  -13   -7    9   -2   -3   -4   -5   -6    0    6   12   18   34   23   22
    G  -21  -22  -18  -20  -14   -8   -2   14    3    2    1    0   -1    5   11   17   23   39   28
    G  -22  -23  -19  -21  -15   -9   -3    3   10   -1    7    6   -2    4   10   16   22   28   44

Estimated Score: 44
------GATGGAACCTGG
AAACCTGATGGAACCTGG
```

## File System Overview
GlobalSequenceAlignment - Eclipse project folder (contains source code)  
&nbsp;/src/  
&nbsp;&nbsp;GlobalAlignment.java - Source code of the Needleman-Wunsch algorithm  
&nbsp;&nbsp;main.java - Driver control method  
LICENSE - GPL-3.0 License  
README.md - This README for repo description  

## Developers
**Author**: Jedadiah McFarland

## References
*Note: These sources were used to design the algorithm along with testing and input data.*  

 
[1] M. Raden et al., “Freiburg RNA tools: a central online resource for RNA-focused research and teaching,” Nucleic Acids Research, vol. 46, no. W1, pp. W25–W29, Jul. 2018, doi: 10.1093/nar/gky329.  
[2] “Interactive demo for Needleman–Wunsch algorithm.” https://bioboot.github.io/bimm143_W20/class-material/nw/ (accessed Oct. 31, 2021).  
[3] M. Raden, M. M. Mohamed, S. M. Ali, and R. Backofen, “Interactive implementations of thermodynamics-based RNA structure and RNA–RNA interaction prediction approaches for example-driven teaching,” PLOS Computational Biology, vol. 14, no. 8, p. e1006341, Aug. 2018, doi: 10.1371/journal.pcbi.1006341.  
[4] “Random DNA Sequence.” https://www.bioinformatics.org/sms2/random_dna.html (accessed Oct. 31, 2021).  
[5] “ROSALIND | Glossary | DNAfull.” http://rosalind.info/glossary/dnafull/ (accessed Oct. 31, 2021).  
[6] B. Chowdhury and G. Garai, “A review on multiple sequence alignment from the perspective of genetic algorithm,” Genomics, vol. 109, no. 5, pp. 419–431, Oct. 2017, doi: 10.1016/j.ygeno.2017.06.007. 

