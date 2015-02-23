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

import java.util.ArrayList;
import java.util.Collection;

final class DefaultPermutationHandlerSelector<E>
    implements PermutationHandlerSelector<E>
{

    private final ArrayList<E> elements;

    private PermutationHandler<E> handler;

    DefaultPermutationHandlerSelector( Collection<E> elements )
    {
        this.elements = new ArrayList<E>( elements );
    }

    public void andHandleWith( PermutationHandler<E> handler )
    {
        this.handler = checkNotNull( handler, "A permutation handler must be specified in order to handle all permutations" );
        heapPermute( elements.size() );
    }

    /*
     * Algorithm implementation inspired by Takanori Ishikawa's impl,
     * see https://gist.github.com/ishikawa/22266
     */
    private void heapPermute( int n )
    {
        if (n == 1)
        {
            handler.onPermutation( new ArrayList<E>( elements ) );
        }
        else
        {
            for ( int i = 0; i < n; i++ )
            {
                heapPermute( n - 1 );

                if ( n % 2 == 1 ) // if n is odd
                {
                    swap( 0, n - 1 );
                }
                else // if n is even
                {
                    swap( i, n - 1 );
                }
            }
        }
    }

    private void swap( int i, int j )
    {
        E swapElement = elements.get( i );
        elements.set( i, elements.get( j ) );
        elements.set( j, swapElement );
    }

}
