import com.api.Comparator;
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;

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

    @Test
    public void testThenComparingUsingComparator()
    {
        abstract class Student
        {
            abstract String name();
            abstract int score();
        }

        final Student student1 = new Student()
        {
            @Override
            String name() {
                return "baa";
            }

            @Override
            int score() {
                return 20;
            }
        };

        final Student student2 = new Student()
        {
            @Override
            String name() {
                return "aaa";
            }

            @Override
            int score() {
                return 19;
            }
        };

        final Student student3 = new Student()
        {
            @Override
            String name() {
                return "zaa";
            }

            @Override
            int score() {
                return 20;
            }
        };

        final Student[] students = new Student[]
        {
            student1,
            student2,
            student3
        };

        final Comparator<Student> byScoreAscending = (leftStudent, rightStudent) -> Integer.compare(leftStudent.score(), rightStudent.score());

        final Comparator<Student> byNameAscending = (leftStudent, rightStudent) -> leftStudent.name().compareTo(rightStudent.name());

        Arrays.sort(students, byScoreAscending.reversed().thenComparing(byNameAscending)::compare);

        final Student[] actual = students;

        final Student[] expected = {
            student1,
            student3,
            student2
        };

        Assert.assertArrayEquals(expected, actual);
    }
}