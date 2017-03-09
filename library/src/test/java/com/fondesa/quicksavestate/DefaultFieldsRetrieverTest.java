package com.fondesa.quicksavestate;

import com.fondesa.quicksavestate.annotation.SaveState;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.lang.reflect.Field;

import static com.fondesa.quicksavestate.FieldMatcher.withNames;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by antoniolig on 05/03/17.
 */
@RunWith(RobolectricTestRunner.class)
public class DefaultFieldsRetrieverTest {

    private DefaultFieldsRetriever mRetriever;

    @Before
    public void initRetriever() {
        mRetriever = new DefaultFieldsRetriever();
    }

    @After
    public void releaseRetriever() {
        mRetriever = null;
    }

    @Test
    public void testRetrieveZeroFields() {
        Field[] fields = mRetriever.getFields(ZeroFields.class);
        assertNotNull(fields);
        assertEquals(fields.length, 0);
    }

    @Test
    public void testRetrieveZeroAnnotatedFields() {
        Field[] fields = mRetriever.getFields(ZeroAnnotatedFields.class);
        assertNotNull(fields);
        assertEquals(fields.length, 0);
    }

    @Test
    public void testRetrievePublicAnnotatedFields() {
        Field[] fields = mRetriever.getFields(PublicAnnotatedFields.class);
        assertNotNull(fields);
        assertThat(fields, withNames("a", "b"));
    }

    @Test
    public void testRetrieveMixedAnnotatedFields() {
        Field[] fields = mRetriever.getFields(MixedPublicFields.class);
        assertNotNull(fields);
        assertThat(fields, withNames("b"));
    }

    @Test
    public void testRetrieveAnnotatedFieldsWithAllModifiers() {
        Field[] fields = mRetriever.getFields(AllModifiersAnnotatedFields.class);
        assertNotNull(fields);
        assertThat(fields, withNames("a", "b", "c", "d"));
    }

    @Test
    public void testRetrieveAnnotatedFieldsInheritance() {
        Field[] fields = mRetriever.getFields(SubClassAllModifiersAnnotatedFields.class);
        assertNotNull(fields);
        assertThat(fields, withNames("a", "b", "c", "d", "e", "f", "g", "h"));
    }

    @Test
    public void testRetrieveCachedAnnotatedFieldsInheritance() {
        Field[] fields = mRetriever.getFields(SubClassAllModifiersAnnotatedFields.class);
        assertNotNull(fields);
        assertThat(fields, withNames("a", "b", "c", "d", "e", "f", "g", "h"));
        /* When the method is called for the second time, the fields will be in cache. */
        fields = mRetriever.getFields(SubClassAllModifiersAnnotatedFields.class);
        assertNotNull(fields);
        assertThat(fields, withNames("a", "b", "c", "d", "e", "f", "g", "h"));
    }

    private static class ZeroFields {
        /* empty */
    }

    private static class ZeroAnnotatedFields {
        private int a;
        protected String b;
        public double c;
        float d;
    }

    private static class PublicAnnotatedFields {
        @SaveState
        public int a;
        @SaveState
        public String b;
    }

    private static class MixedPublicFields {
        public int a;
        @SaveState
        public String b;
    }

    private static class AllModifiersAnnotatedFields {
        private int _a;
        protected String _b;
        public double _c;
        float _d;

        @SaveState
        private int a;
        @SaveState
        protected String b;
        @SaveState
        public double c;
        @SaveState
        float d;
    }

    private static class SubClassAllModifiersAnnotatedFields extends AllModifiersAnnotatedFields {
        private int _e;
        protected String _f;
        public double _g;
        float _h;

        @SaveState
        private int e;
        @SaveState
        protected String f;
        @SaveState
        public double g;
        @SaveState
        float h;
    }
}