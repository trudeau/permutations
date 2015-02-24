permutations
=============

Elements permutations implementation

# Usage

This is a small set of fluent APIs to generate permutations of an arbitrary elements collection.

## Calculate the number of permutations of elements

The `org.nnsoft.trudeau.math.permutations.Permutations` contains a couple of methods to calculate the number of permutations that can be generated starting from an array or collection of elements.

```
import static org.nnsoft.trudeau.math.permutations.Permutations.numberOfPermutations;

...

// it can be applied to an array of int, for example
int numberOfPermutations = numberOfPermutations( 1, 2, 3 ); // that will return 6, which is 3!
```

## Handling Permutations

The `org.nnsoft.trudeau.math.permutations.Permutations#permute()` methods allow generating all the permutations given an array or collection of elements.
The implementation is based on the [Heap's algorithm](http://en.wikipedia.org/wiki/Heap%27s_algorithm) which is an optimized recursive algorithm to generate permutations.

Generated permutations can be handled via a `org.nnsoft.trudeau.math.permutations.PermutationHandler` like in the sample below, where:

 * `onPermutation( E...permutation )` method is invoked every time a new permutation is generated;

 * `onCompleted()` method is invoked once the permutations generation is complete and returns the result of the handle.

```
import static org.nnsoft.trudeau.math.permutations.Permutations.permute;
import static org.nnsoft.trudeau.math.permutations.VisitState.CONTINUE;

...

permute( 1, 2, 3 ).andHandleWith( new PermutationHandler<Integer, Void>()
{

    public VisitState onPermutation( Integer...permutation )
    {
        System.out.println( Arrays.toString( permutation ) );
        return CONTINUE;
    }

    public Void onCompleted()
    {
        return null;
    }

} );
```

The `org.nnsoft.trudeau.math.permutations.VisitState` status is used to abort/continue the flow of generating permutations; maybe users are looking for a specific configuration, when found the enumeration of the permutations can be blocked (just a silly example below):

```
import static org.nnsoft.trudeau.math.permutations.Permutations.permute;
import static org.nnsoft.trudeau.math.permutations.VisitState.ABORT;
import static org.nnsoft.trudeau.math.permutations.VisitState.CONTINUE;

...

boolean configurationFound = permute( 1, 2, 3 )
.andHandleWith( new PermutationHandler<Integer, Boolean>()
{

    private boolean found = false;

    public VisitState onPermutation( Integer...permutation )
    {
        System.out.println( Arrays.toString( permutation ) );

        if ( permutation[0] == 3 )
        {
            found = true;
            return ABORT;
        }

        return CONTINUE;
    }

    public Boolean onCompleted()
    {
        return true;
    }

} );
```

## Just get all Permutations

If users needs to keep all generated permutations to a data structure, the `org.nnsoft.trudeau.math.permutations.Permutations#enumerateAllPermutations()` is a utility method that build a collection of permutations:

```
import static org.nnsoft.trudeau.math.permutations.Permutations.enumerateAllPermutations;

...

Collection<Integer[]> allPermutations = enumerateAllPermutations( 1, 2, 3 );
```

# Note

Please pay attention on the fact that the methods above are overloaded in order to accept both arrays and collections.
