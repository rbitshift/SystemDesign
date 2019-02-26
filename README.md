# System Design

Topics:
  - Design Patterns
    - GoF
  - OOPS Design
  - System Design
    - POEAA - Martin Fowler
    - DDIA - Martin Kleppmann
    - DDD - Eric Evans
    - Building Microservices - Sam Newman
    
 Resources:
 - Preparation
    - Gain.lo
    - HiredinTech.com
    - Educative.io 
    - https://www.youtube.com/playlist?list=PLrmLmBdmIlps7GJJWW9I7N0P0rB0C3eY2
    - https://github.com/donnemartin/system-design-primer

Questions:
- Desiging URL shortner
  1. http://blog.gainlo.co/index.php/2016/03/08/system-design-interview-question-create-tinyurl-system/
  2. https://www.interviewbit.com/problems/design-url-shortener/
  3. https://www.youtube.com/watch?v=fMZMm_0ZhK4&t=139s
- Designing Twitter

OOPS Design:
1. Tic Tac Toe Desgin
2. Snake and Ladders Design
3. Ludo Design
4. Elevetor System Design
5. Parking System Design


Articles:

Understanding ACID:
Atommicity: Atomic refers to something that cannot be broken down into smaller parts. In multi-threaded programming, if one thread executes an atomic operation, that means there is no way that another thread could see the half-finished result of the operation. The system can only be in the state it was before the operation or after the operation, not something in between.
ACID atomicity describes what happens if a client wants to make several writes, but a fault occurs after some of the writes have been processed—for example, a process crashes, a network connection is interrupted, a disk becomes full, or some integrity constraint is violated. If the writes are grouped together into an atomic transaction, and the transaction cannot be completed (committed) due to a fault, then the transaction is aborted and the database must discard or undo any writes it has made so far in that transaction.

Consistency: Consistency is overloaded term. Cositency Models in context of Replication, Consistent Hashing in context of rebalanching, linearizability in context of CAP Theorem, ACID consitency refers to application specific notion of database being in good state. The idea of ACID consistency is that you have certain statements about your data (invariants) that must always be true. Idea of consistency depends on the application’s notion of invariants, and it’s the application’s responsibility to define its transactions correctly so that they preserve consistency. Some specific kinds of invariants can be checked by the database, for example using foreign key constraints or uniqueness constraints. The application may rely on the database’s atomicity and isolation properties in order to achieve consistency, but it’s not up to the database alone. Thus, the letter C doesn’t really belong in ACID.

Isolation: Isolation in the sense of ACID means that concurrently executing transactions are isolated from each other: they cannot step on each other’s toes. The classic database textbooks formalize isolation as serializability, which means that each transaction can
pretend that it is the only transaction running on the entire database. The database ensures that when the transactions have committed, the result is the same as if they had run serially (one after another), even though in reality they may have run concurrently. In practice, serializable isolation is rarely used, because it carries a performance penalty. Some popular databases, such as Oracle 11g, don’t even implement it. In Oracle there is an isolation level called “serializable,” but it actually implements something called snapshot isolation, which is a weaker guarantee than serializability

Durability: Durability is the promise that once a transaction has committed successfully, any data it has written will not be forgotten, even if there is a hardware fault or the database crashes.

[Reference: Designing Data Intensive Application by Martin Klepmann]

Understanding CAP:
- [CAP Theorem](https://en.wikipedia.org/wiki/CAP_theorem)
- [Eric Brewer's CAP Revisiting](http://www.infoq.com/articles/cap-twelve-years-later-how-the-rules-have-changed)
- [Coda Hale's You Can't Sacrifice Partition Tolerance](http://codahale.com/you-cant-sacrifice-partition-tolerance)
- [Henry Robinson's CAP FAQ](https://github.com/henryr/cap-faq)
- [Martin Klepmann's Please stop calling databases CP or AP](https://martin.kleppmann.com/2015/05/11/please-stop-calling-databases-cp-or-ap.html)
- [Linearizability vs Serializability by Peter Bailis](http://www.bailis.org/blog/linearizability-versus-serializability)
- [Problems with CAP by Daniel Abadi](http://dbmsmusings.blogspot.com/2010/04/problems-with-cap-and-yahoos-little.html)
- [Nathan Marz's How to Beat the CAP Theorem](http://nathanmarz.com/blog/how-to-beat-the-cap-theorem.html): Lambda Architecture
- [Jay Krep's Questioning the Lambda Architecture](https://www.oreilly.com/ideas/questioning-the-lambda-architecture)

Consistency: Consistency in CAP actually means linearizability, which is a very specific (and very strong) notion of consistency. In particular it has got nothing to do with the C in ACID, even though that C also stands for “consistency”.

Linearizability is a guarantee about single operations on single objects. It provides a real-time (i.e., wall-clock) guarantee on the behavior of a set of single operations (often reads and writes) on a single object (e.g., distributed register or data item). In plain English, under linearizability, writes should appear to be instantaneous. Imprecisely, once a write completes, all later reads (where “later” is defined by wall-clock start time) should return the value of that write or the value of a later write. Once a read returns a particular value, all later reads should return that value or the value of a later write. Linearizability for read and write operations is synonymous with the term “atomic consistency” and is the “C,” or “consistency,” in Gilbert and Lynch’s proof of the CAP Theorem. We say linearizability is composable (or “local”) because, if operations on each object in a system are linearizable, then all operations in the system are linearizable.

Availability: Availability in CAP is defined as “every request received by a non-failing [database] node in the system must result in a [non-error] response”. It’s not sufficient for some node to be able to handle the request: any non-failing node needs to be able to handle it. Many so-called “highly available” (i.e. low downtime) systems actually do not meet this definition of availability.

Partition Tolerance: Partition Tolerance (terribly mis-named) basically means that you’re communicating over an asynchronous network that may delay or drop messages. The internet and all our datacenters have this property, so you don’t really have any choice in this matter. 

The CAP system model is a single, read-write register – that’s all. For example, the CAP theorem says nothing about transactions that touch multiple objects: they are simply out of scope of the theorem, unless you can somehow reduce them down to a single register. CAP theorem says nothing about latency, which people tend to care about more than availability. In fact, CAP-available systems are allowed to be arbitrarily slow to respond, and can still be called “available”. Going out on a limb, I’d guess that your users wouldn’t call your system “available” if it takes 2 minutes to load a page.

PACEL: As per Daniel Abadi, CAP should really be PACELC. If there is a partition (P) how does the system tradeoff between availability and consistency (A and C); else (E) when the system is running as normal in the absence of partitions, how does the system tradeoff between latency (L) and consistency (C)? Systems that tend to give up consistency for availability when there is a partition also tend to give up consistency for latency when there is no partition. This is the source of the asymmetry of the C and A in CAP. However, this confusion is not present in PACELC.

Examples:
Amazon’s Dynamo (and related systems like Cassandra and SimpleDB) are PA/EL in PACELC --- upon a partition, they give up consistency for availability; and under normal operation they give up consistency for lower latency. Giving up C in both parts of PACELC makes the design simpler --- once the application is configured to be able to handle inconsistencies, it makes sense to give up consistency for both availability and lower latency.

Fully ACID systems are PC/EC in PACELC. They refuse to give up consistency, and will pay the availability and latency costs to achieve it.

However, there are some interesting counterexamples where the C’s of PACELC are not correlated. One such example is PNUTS, which is PC/EL in PACELC. In normal operation they give up consistency for latency; however, upon a partition they don’t give up any additional consistency (rather they give up availability).

Linearizability vs Serializability:
Linearizability is a guarantee about single operations on single objects. It provides a real-time (i.e., wall-clock) guarantee on the behavior of a set of single operations (often reads and writes) on a single object (e.g., distributed register or data item).

Serializability is a guarantee about transactions, or groups of one or more operations over one or more objects. It guarantees that the execution of a set of transactions (usually containing read and write operations) over multiple items is equivalent to some serial execution (total ordering) of the transactions. Unlike linearizability, serializability does not—by itself—impose any real-time constraints on the ordering of transactions. 

Combining serializability and linearizability yields strict serializability: transaction behavior is equivalent to some serial execution, and the serial order corresponds to real time. For example, say I begin and commit transaction T1, which writes to item x, and you later begin and commit transaction T2, which reads from x. A database providing strict serializability for these transactions will place T1 before T2 in the serial ordering, and T2 will read T1’s write. A database providing serializability (but not strict serializability) could order T2 before T1.2

As Herlihy and Wing note, “linearizability can be viewed as a special case of strict serializability where transactions are restricted to consist of a single operation applied to a single object.”

=> Linearizable itself composed of serializability, e.g. Synchronization mechanism provides linearizability and serializability or strict-serializability, but can we can provide serilizability alone in a weaker sense e.g. Read Committed, Snapshot Isolation / Repeatable Read etc.
