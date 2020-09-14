package cn.logcode.xhufiveface.model.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @package cn.logcode.demo.model.vo
 * @ClassName ValidTest
 * @Author caost
 * @Date 2020/4/10 10:59
 * @Emall caostgrace@163.com
 * @DESC
 */

@Data
public class ValidTest {

    @NotNull(message = "传入的姓名为null，请传值")
    @NotEmpty(message = "传入的姓名为空字符串，请传值")
    public String name;

    @NotNull(message = "传入的分数为null，请传值")
    @Min(value = 0, message = "传入的学生成绩有误，分数应该在0~100之间")
    @Max(value = 10, message = "传入的学生成绩有误，分数应该在0~100之间")
    public Integer score;

    @NotNull(message = "传入的电话为null，请传值")
    @NotEmpty(message = "传入的电话为空字符串，请传值")
    @Length(min = 11, max = 11, message = "传入的电话号码长度有误，必须为11位")
    public String mobile;

}
