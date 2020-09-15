package cn.logcode.xhufiveface.service.core;


import cn.logcode.xhufiveface.dao.mapper.FaceStorageMapper;
import cn.logcode.xhufiveface.dao.pojo.FaceStorage;
import cn.logcode.xhufiveface.dao.pojo.FaceStorageExample;
import cn.logcode.xhufiveface.storage.Storage;
import com.github.pagehelper.PageHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @package cn.logcode.demo.service.core
 * @ClassName StorageService
 * @Author caost
 * @Date 2020/4/8 16:00
 * @Emall caostgrace@163.com
 * @DESC
 */

@Service
public class CoreStorageService {

    public final Log logger = LogFactory.getLog(CoreStorageService.class);

    @Autowired
    public FaceStorageMapper storageMapper;

    public void deleteByKey(String key) {
        FaceStorageExample example = new FaceStorageExample();
        example.or().andFileKeyEqualTo(key);
        storageMapper.deleteByExample(example);
    }

    public void add(FaceStorage storageInfo) {
        storageInfo.setAddTime(new Date());
        storageInfo.setUpdateTime(new Date());
        storageMapper.insertSelective(storageInfo);
    }

    public FaceStorage findByKey(String key) {
        FaceStorageExample example = new FaceStorageExample();
        example.or().andFileKeyEqualTo(key);
        List<FaceStorage> list = storageMapper.selectByExample(example);
        return list.size() != 0 ? list.get(0) : null;
    }

    public int update(FaceStorage storageInfo) {
        storageInfo.setUpdateTime(new Date());
        return storageMapper.updateByPrimaryKeySelective(storageInfo);
    }

    public FaceStorage findById(Integer id) {
        return storageMapper.selectByPrimaryKey(id);
    }

    public List<FaceStorage> querySelective(String key, String name, Integer page, Integer limit, String sort, String order) {
        FaceStorageExample example = new FaceStorageExample();
        FaceStorageExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(key)) {
            criteria.andFileKeyEqualTo(key);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return storageMapper.selectByExample(example);
    }


    public List<FaceStorage> getByIds(List<Integer> ids){
        FaceStorageExample example = new FaceStorageExample();
        FaceStorageExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return storageMapper.selectByExample(example);
    }

}
