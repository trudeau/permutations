permutations
=============

Elements collection permutations implementation

# Usage

This is a small set of fluent APIs to generate permutations of an arbitrary elements collection.

## Calculate the number of permutations of an elements set

The `org.nnsoft.trudeau.permutations.Permutations` contains a couple of methods to calculate the number of permutations that can be generated starting from an array or collection of elements.

```
import static org.nnsoft.trudeau.permutations.Permutations.numberOfPermutations;

...

// it can be applied to an array of int, for example
int numberOfPermutations = numberOfPermutations( 1, 2, 3 ); // that will return 6, which is 3!

// or it can be applied to a collection of String, for example
Collection<String> stringCollection = new ArrayList<String>();
stringCollection.add( "a" );
stringCollection.add( "b" );
stringCollection.add( "c" );
stringCollection.add( "d" );
numberOfPermutations = numberOfPermutations( stringCollection ); // that will return 24, which is 4!
```

## Handling Permutations

The `org.nnsoft.trudeau.permutations.Permutations#permute()` methods allow generating all the permutations given an array or collection of elements.
The implementation is based on the [Heap's algorithm](http://en.wikipedia.org/wiki/Heap%27s_algorithm) which is an optimized recursive algorithm to generate permutations.
Generated permutations can be handled via a `org.nnsoft.trudeau.permutations.PermutationHandler` like in the sample below:

```
import static org.nnsoft.trudeau.permutations.Permutations.permute;
import static org.nnsoft.trudeau.permutations.VisitState.CONTINUE;

...

permute( 1, 2, 3 ).andHandleWith( new PermutationHandler<Integer>()
{

    public VisitState onPermutation( Collection<Integer> permutation )
    {
        System.out.println( permutation );
        return CONTINUE;
    }

} );
```

The `org.nnsoft.trudeau.permutations.VisitState` status is used to abort/continue the flow of generating permutations; maybe users are looking for a specific configuration, when found the enumeration of the permutations can be blocked (just a silly example below):

```
import static org.nnsoft.trudeau.permutations.Permutations.permute;
import static org.nnsoft.trudeau.permutations.VisitState.ABORT;
import static org.nnsoft.trudeau.permutations.VisitState.CONTINUE;

...

permute( 1, 2, 3 ).andHandleWith( new PermutationHandler<Integer>()
{

    public VisitState onPermutation( Collection<Integer> permutation )
    {
        System.out.println( permutation );
        if ( permutation.iterator().next() == 3 )
        {
            return VisitState.ABORT;
        }
        return VisitState.CONTINUE;
    }

} );
```

## Just get all Permutations

If users needs to keep all generated permutations to a data structure, the `org.nnsoft.trudeau.permutations.Permutations#enumerateAllPermutations()` is a utility method that build a collection of permutations:

```
import static org.nnsoft.trudeau.permutations.Permutations.enumerateAllPermutations;

...

Collection<Collection<Integer>> allPermutations = enumerateAllPermutations( 1, 2, 3 );
```

# Note

Please pay attention on the fact that the method above is overloaded in order to accept both arrays and collections.
