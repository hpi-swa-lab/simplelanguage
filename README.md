# SimpleLanguage 

Implemented during the course "Virtual Execution Environments" in 2018 by Jonas Beyer and Philipp Otto.

We added the following features:

### N-ary value classes and value class optimization

An implementation of an N-ary value classes, that supports inlining of other value classes as described in [1].

N-ary objects are created via the builtin `vnew(a, b, c)` and values are accessed via `get(object, index)`.

For an example, see `benchmark/TreeTraversal.sl`.

See 
-  `SLValueObject`
-  `SLNaryValueObject`
-  `SLShape`
-  `SLValueObjectBuiltin`
-  `SLValueObjectAccessBuiltin`

### Value class optimization via Truffle Shapes

In an earlier implementation, we tried to implement the value class optimization [1] using Truffle Shapes, which are usually used to implement dynamic objects within Truffle.

We implemented it for cons cells, which are created with `cell = consDoWs(a,b)`.
See 

In the end, Truffle `DynamicObject` was too slow for this kind of optimization.

See
-  `SLShapeWrapper`
-  `SLConsCellBuiltinDynamicObjectWithShapes`

### Cons Cells

An implementation of a cons cell class that holds two Objects.

Builtins:
-  `cons(a,b)`, creates a cons cell of objects `a` and `b`
-  `head(a)`, accesses first object of cell `a`
-  `tail(a)`, accesses second object of cell `a`

Additionally, we implemented a cons cell that is specialized for `Long` values, see `SLLongConsCell`, it is created with the builtin `consWs`.
Another implementation is provided, that uses `ValueProfile` to analyze types of the cons cell contents, which is created with `consWvp`.

In `SLConsCellBuiltin` we tried out inlining of cons cells as a first evaluation towards [1].

See 
-  `SLConsCell` and `SLConsCell2Builtin`
-  `SLLongConsCell` and `SLConsCellBuiltinWithSpecialization`
-  `SLProfileConsCell` and `SLConsCellBuiltinWithValueProfiles`

### Cons Cell using `DynamicObject`

An implementation of cons cells using the `DynamicObject` class provided by Truffle.

It is created with `cell = consDo(a,b)` and values are accessed via `cell.head` and `cell.tail`.

We used it to benchmark against our cons cell implementation and it turned out considerably slower.

See
-  Truffle `DynamicObject`
-  `SLConsCellBuiltingDynamicObject`

### References

[1] Pape et al.: Adaptive Just-in-time Value Class Optimization
for Lowering Memory Consumption and Improving Execution Time
Performance
