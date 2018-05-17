package cn.itproject.crm.test.dao.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import cn.itproject.crm.dao.ReleaseCommonPoolDao;
import cn.itproject.crm.test.BaseSpringTestSupport;

public class TestReleaseCommonPoolImpl extends BaseSpringTestSupport {
	@Resource
	private ReleaseCommonPoolDao releaseCommonPoolDao;

	@Test
	public void testRelease() throws Exception {
		List<Integer> ids = Arrays.asList(3697, 1099, 1066, 2164);

		releaseCommonPoolDao.release(ids, 2, 1);
	}
}
