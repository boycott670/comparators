import org.junit.Assert;
import org.junit.Test;

public class ComparatorTest
{
    @Test
    public void testReversed()
    {
        final Comparator<Integer> comparator = Integer::compare;

        final int left = 1, right = 2;

        final boolean expectedLeftBeSmaller = true;

        final boolean actual = comparator.reversed().compare(right, left) < 0;

        Assert.assertEquals(expectedLeftBeSmaller, actual);
    }
}