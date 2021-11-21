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
These samples were created using [Sequence Manipulation Site](https://www.bioinformatics.org/sms2/random_dna.html).  
These samples were primarily tested for accuracy using [UC San Diego Global Alignment App](https://bioboot.github.io/bimm143_W20/class-material/nw/)

**Sample Input (1)**: 
```
Enter the first DNA sequence: acccaacggcag
Enter the second DNA sequence: caagaaaccgat
```
**Expected Output (1)**: 
```
              C    A    A    G    A    A    A    C    C    G    A    T
         0   -1   -2   -3   -4   -5   -6   -7   -8   -9  -10  -11  -12
    A   -1   -1    1    0   -1   -2   -3   -4   -5   -6   -7   -8   -9
    C   -2    1    0    0   -1   -2   -3   -4   -2   -3   -4   -5   -6
    C   -3    0    0   -1   -1   -2   -3   -4   -2    0   -1   -2   -3
    C   -4   -1   -1   -1   -2   -2   -3   -4   -2    0   -1   -2   -3
    A   -5   -2    1    1    0    0    0   -1   -2   -1   -1    1    0
    A   -6   -3    0    3    2    2    2    2    1    0   -1    1    0
    C   -7   -4   -1    2    2    1    1    1    4    3    2    1    0
    G   -8   -5   -2    1    4    3    2    1    3    3    5    4    3
    G   -9   -6   -3    0    3    3    2    1    2    2    5    4    3
    C  -10   -7   -4   -1    2    2    2    1    3    4    4    4    3
    A  -11   -8   -5   -2    1    4    4    4    3    3    3    6    5
    G  -12   -9   -6   -3    0    3    3    3    3    2    5    5    5

Estimated Score: 5
-ACCCAACGGCAG
AAAGAAACCG-AT
```
**Sample Input (2)**:
```
Enter the first DNA sequence: cgtgtaattcag
Enter the second DNA sequence:  ccacactgtact
```
**Expected Output (2)**: 
```
              C    C    A    C    A    C    T    G    T    A    C    T
         0   -1   -2   -3   -4   -5   -6   -7   -8   -9  -10  -11  -12
    C   -1    2    1    0   -1   -2   -3   -4   -5   -6   -7   -8   -9
    G   -2    1    1    0   -1   -2   -3   -4   -2   -3   -4   -5   -6
    T   -3    0    0    0   -1   -2   -3   -1   -2    0   -1   -2   -3
    G   -4   -1   -1   -1   -1   -2   -3   -2    1    0   -1   -2   -3
    T   -5   -2   -2   -2   -2   -2   -3   -1    0    3    2    1    0
    A   -6   -3   -3    0   -1    0   -1   -2   -1    2    5    4    3
    A   -7   -4   -4   -1   -1    1    0   -1   -2    1    4    4    3
    T   -8   -5   -5   -2   -2    0    0    2    1    0    3    3    6
    T   -9   -6   -6   -3   -3   -1   -1    2    1    3    2    2    5
    C  -10   -7   -4   -4   -1   -2    1    1    1    2    2    4    4
    A  -11   -8   -5   -2   -2    1    0    0    0    1    4    3    3
    G  -12   -9   -6   -3   -3    0    0   -1    2    1    3    3    2

Estimated Score: 2
CGTGTA-A-T-TCA-G
C---CACACTGT-ACT
```

## File System Overview
GlobalSequenceAlignment - Eclipse project folder (contains source code)  
&nbsp;src/com/globalsequencealignment  
&nbsp;&nbsp;GlobalAlignment.java - Source code of the Needleman-Wunsch algorithm  
&nbsp;&nbsp;main.java - Driver control method  
&nbsp;&nbsp;package-info.java - Eclipse package designation  
LICENSE - GPL-3.0 License  
README.md - This README for repo description  

## Developers
**Author**: Jedadiah McFarland

## References
*Note: These sources were used to design the algorithm along with testing and input data.*  

B. Chowdhury and G. Garai, “A review on multiple sequence alignment from the perspective of genetic algorithm,” Genomics, vol. 109, no. 5, pp. 419–431, Oct. 2017, doi: 10.1016/j.ygeno.2017.06.007.  
“Interactive demo for Needleman–Wunsch algorithm.” https://bioboot.github.io/bimm143_W20/class-material/nw/ (accessed Oct. 31, 2021).  
“Random DNA Sequence.” https://www.bioinformatics.org/sms2/random_dna.html (accessed Oct. 31, 2021).  
“ROSALIND | Glossary | DNAfull.” http://rosalind.info/glossary/dnafull/ (accessed Oct. 31, 2021).
W. R. Pearson, “Selecting the Right Similarity-Scoring Matrix,” Curr Protoc Bioinformatics, vol. 43, p. 3.5.1-3.5.9, 2013, doi: 10.1002/0471250953.bi0305s43.  

# TODO (Dev) 

- Add affine gap penalties (favor longer gaps as opposed to many small gaps)
