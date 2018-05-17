package cn.itproject.crm.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 其他信息
 * @author ham
 *
 */
@Entity
@Table(name="OtherInfo")
public class OtherInfo implements Serializable{
	private static final long serialVersionUID = 8078816670969114660L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
}
