package it.raymanrt.orient.query;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static it.raymanrt.orient.query.Projection.projection;
import static it.raymanrt.orient.query.ProjectionFunction.in;
import static it.raymanrt.orient.query.ProjectionFunction.max;
import static it.raymanrt.orient.query.ProjectionFunction.out;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ProjectionMethodTest {

	@Test
	public void indexTest() {
		String simpleField = "clause";
		Projection p = projection(simpleField).index(5);
		assertEquals("clause[5]", p.toString());
		assertEquals("clause", p.getName());

		p = projection(simpleField).indexRange(5, 10);
		assertEquals("clause[5-10]", p.toString());

		p = projection(simpleField).index(5, 10);
		assertEquals("clause[5, 10]", p.toString());

		p = projection(simpleField).index(5, 10, 15);
		assertEquals("clause[5, 10, 15]", p.toString());
	}

	@Test
	public void fieldTest() {
		String simpleField = "clause";
		Projection p = projection(simpleField).field("f1");
		assertEquals("clause[f1]", p.toString());
		assertEquals("clause", p.getName());

		p = projection(simpleField).field("f1", "f2", "f3");
		assertEquals("clause[f1, f2, f3]", p.toString());
	}

	@Test
	public void appendTest() {
		String simpleField = "clause";
		Projection p = projection(simpleField).append("suffix");
		assertEquals("clause.append('suffix')", p.toString());

		p = projection(simpleField).append(max(out().index(0).field("name")));
		assertEquals("clause.append(max(out()[0][name]))", p.toString());

		p = projection(simpleField).append("'''");
		assertEquals("clause.append('\'\'\'')", p.toString());
	}

	@Test
	public void asBooleanTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).asBoolean();
		assertEquals("field.asBoolean()", p.toString());
	}

	@Test
	public void asDateTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).asDate();
		assertEquals("field.asDate()", p.toString());
	}

	@Test
	public void asDateTimeTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).asDateTime();
		assertEquals("field.asDateTime()", p.toString());
	}

	@Test
	public void asDecimalTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).asDecimal();
		assertEquals("field.asDecimal()", p.toString());
	}

	@Test
	public void asFloatTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).asFloat();
		assertEquals("field.asFloat()", p.toString());
	}

	@Test
	public void asIntegerTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).asInteger();
		assertEquals("field.asInteger()", p.toString());
	}

	@Test
	public void asListTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).asList();
		assertEquals("field.asList()", p.toString());
	}

	@Test
	public void asLongTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).asLong();
		assertEquals("field.asLong()", p.toString());
	}

	@Test
	public void asMapTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).asMap();
		assertEquals("field.asMap()", p.toString());
	}

	@Test
	public void asSetTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).asSet();
		assertEquals("field.asSet()", p.toString());
	}

	@Test
	public void asStringTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).asString();
		assertEquals("field.asString()", p.toString());
	}

	@Test
	public void charAtTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).charAt(4);
		assertEquals("field.charAt(4)", p.toString());
	}

	@Test
	public void convertTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).convert(DataType.BOOLEAN);
		assertEquals("field.convert('BOOLEAN')", p.toString());

		p = projection(simpleField).convert(DataType.DATE);
		assertEquals("field.convert('DATE')", p.toString());

		p = projection(simpleField).convert(DataType.LIST);
		assertEquals("field.convert('LIST')", p.toString());

		p = projection(simpleField).convert(DataType.STRING).append(" - ").append("suffix");
		assertEquals("field.convert('STRING').append(' - ').append('suffix')", p.toString());
	}

	@Test
	public void excludeTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).exclude("a");
		assertEquals("field.exclude('a')", p.toString());

		p = projection(simpleField).exclude("a", "b", "c");
		assertEquals("field.exclude('a', 'b', 'c')", p.toString());
	}

	@Test
	public void formatTest() {
		String simpleField = "salary";
		Projection p = projection(simpleField).format("%-011d");
		assertEquals("salary.format('%-011d')", p.toString());
	}

	@Test
	public void hashTest() {
		String simpleField = "password";
		Projection p = projection(simpleField).hash();
		assertEquals("password.hash()", p.toString());

		try {
			p = projection(simpleField).hash(MessageDigest.getInstance("SHA-512"));
			assertEquals("password.hash('SHA-512')", p.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void includeTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).include("a");
		assertEquals("field.include('a')", p.toString());

		p = projection(simpleField).include("a", "b", "c");
		assertEquals("field.include('a', 'b', 'c')", p.toString());
	}

	@Test
	public void indexOfTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).indexOf("b'aaah");
		assertEquals("field.indexOf('b\\'aaah')", p.toString());

		p = projection(simpleField).indexOf("b'aaah", 5);
		assertEquals("field.indexOf('b\\'aaah', 5)", p.toString());
	}

	@Test
	public void javaTypeTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).javaType();
		assertEquals("field.javaType()", p.toString());
	}

	@Test
	public void keysTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).keys();
		assertEquals("field.keys()", p.toString());
	}

	@Test
	public void leftTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).left(1);
		assertEquals("field.left(1)", p.toString());
	}

	@Test
	public void lengthTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).length();
		assertEquals("field.length()", p.toString());
	}

	@Test
	public void normalizeTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).normalize();
		assertEquals("field.normalize()", p.toString());

		p = projection(simpleField).normalize("NFD");
		assertEquals("field.normalize('NFD')", p.toString());

		p = projection(simpleField).normalize("NFD", "xxx");
		assertEquals("field.normalize('NFD', 'xxx')", p.toString());
	}

	@Test
	public void prefixTest() {
		String simpleField = "clause";
		Projection p = projection(simpleField).prefix("prefix");
		assertEquals("clause.prefix('prefix')", p.toString());

		p = projection(simpleField).prefix(max(out().index(0).field("name")));
		assertEquals("clause.prefix(max(out()[0][name]))", p.toString());

		p = projection(simpleField).prefix("'''");
		assertEquals("clause.prefix('\'\'\'')", p.toString());
	}

	@Test
	public void removeTest() {
		Projection p = out().dot(in()).remove(Variable.thisRecord());
		assertEquals("out().in().remove(@this)", p.toString());
	}

	@Test
	public void removeAllTest() {
		Projection p = out().dot(in()).removeAll(out("edge"));
		assertEquals("out().in().removeAll(out('edge'))", p.toString());
	}

	@Test
	public void replaceTest() {
		Projection p = projection("field").replace("hell'o", "w'orld");
		assertEquals("field.replace('hell\\'o', 'w\\'orld')", p.toString());
	}

	@Test
	public void rightTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).right(10);
		assertEquals("field.right(10)", p.toString());
	}

	@Test
	public void sizeTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).size();
		assertEquals("field.size()", p.toString());
	}

	@Test
	public void subStringTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).subString(0);
		assertEquals("field.subString(0)", p.toString());

		p = projection(simpleField).subString(0, 10);
		assertEquals("field.subString(0, 10)", p.toString());
	}

	@Test
	public void trimTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).trim();
		assertEquals("field.trim()", p.toString());
	}

	@Test
	public void toJsonTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).toJson();
		assertEquals("field.toJson()", p.toString());

		try {
			projection(simpleField).toJson("format");
		} catch (UnsupportedOperationException ex) {
			assertEquals("not implemented yet", ex.getMessage());
		}
	}

	@Test
	public void toLowerCaseTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).toLowerCase();
		assertEquals("field.toLowerCase()", p.toString());
	}

	@Test
	public void toUpperCaseTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).toUpperCase();
		assertEquals("field.toUpperCase()", p.toString());
	}

	@Test
	public void typeTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).type();
		assertEquals("field.type()", p.toString());
	}

	@Test
	public void valuesTest() {
		String simpleField = "field";
		Projection p = projection(simpleField).values();
		assertEquals("field.values()", p.toString());
	}

}