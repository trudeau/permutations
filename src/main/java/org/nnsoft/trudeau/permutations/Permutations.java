package org.nnsoft.trudeau.permutations;

/*
 *   Copyright 2015 The Trudeau Project
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

import static org.nnsoft.trudeau.utils.Assertions.checkNotNull;
import static java.util.Arrays.asList;

import java.util.Collection;

/**
 * A commodity utility class to calculate permutations of elements.
 */
public class Permutations
{

    /**
     * Hidden constructor, this class cannot be instantiated.
     */
    private Permutations()
    {
        // do nothing
    }

    /**
     * Calculates the number of all possible permutations of the given elements array.
     *
     * @param elements the array for whom all possible permutations have to be calculated.
     * @return the number of all possible permutations of the given elements array.
     */
    public static <E> int numberOfPermutations( E...elements )
    {
        elements = checkNotNull( elements, "Impossible to calculate the number of permutations for null elements array" );

        return factorial( elements.length );
    }

    /**
     * Calculates the number of all possible permutations of the given elements collection.
     *
     * @param elements the collection for whom all possible permutations have to be calculated.
     * @return the number of all possible permutations of the given elements collection.
     */
    public static <E> int numberOfPermutations( Collection<E> elements )
    {
        elements = checkNotNull( elements, "Impossible to calculate the number of permutations for null elements collection" );

        return factorial( elements.size() );
    }

    /**
     * Method that calculates the factorial of an input number.
     *
     * @param n the number for whom the factorial has to be calculated.
     * @return the factorial of the input number.
     */
    private static int factorial( int n )
    {
        if ( n == 0 || n == 1 )
        {
            return 1;
        }

        return factorial( n - 1 ) * n;
    }

    /**
     * 
     *
     * @param elements
     * @return
     */
    public static <E> PermutationHandlerSelector<E> permute( E...elements )
    {
        elements = checkNotNull( elements, "Impossible to calculate all permutations for null elements array" );

        return permute( asList( elements ) );
    }

    public static <E> PermutationHandlerSelector<E> permute( Collection<E> elements )
    {
        elements = checkNotNull( elements, "Impossible to calculate all permutations for null elements collection" );

        return new DefaultPermutationHandlerSelector<E>( elements );
    }

    public static <E> Iterable<Collection<E>> enumerateAllPermutations( E...elements )
    {
        elements = checkNotNull( elements, "Impossible to enumerate all permutations for null elements array" );

        return enumerateAllPermutations( asList( elements ) );
    }

    public static <E> Collection<Collection<E>> enumerateAllPermutations( Collection<E> elements )
    {
        AccumulatorPermutationHandler<E> handler = new AccumulatorPermutationHandler<E>();
        permute( elements ).andHandleWith( handler );
        return handler.getAllPermutations();
    }

    public static void main( String[] args )
    {
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
    }

}
