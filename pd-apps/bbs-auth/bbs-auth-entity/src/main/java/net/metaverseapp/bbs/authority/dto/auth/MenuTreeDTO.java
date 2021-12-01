package net.metaverseapp.bbs.authority.dto.auth;

import com.itheima.pinda.model.ITreeNode;
import io.swagger.annotations.ApiModel;
import lombok.ToString;
import net.metaverseapp.bbs.authority.entity.auth.Menu;

import java.util.List;

/**
 * ResourceTreeDTO
 *
 */
@ToString(callSuper = true)
@ApiModel(value = "ResourceTreeDTO", description = "ResourceTreeDTO")
public class MenuTreeDTO extends Menu implements ITreeNode<MenuTreeDTO, Long> {
    private List<MenuTreeDTO> children;

    private String label;

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public Long getCreateUser() {
        return super.getCreateUser();
    }

    @Override
    public Long getUpdateUser() {
        return super.getUpdateUser();
    }

    @Override
    public List<MenuTreeDTO> getChildren() {
        return this.children;
    }

    @Override
    public void setChildren(List<MenuTreeDTO> children) {
        this.children = children;
    }

    public String getLabel() {
        return this.getName();
    }

    //    @Override
//    public boolean equals(Object o) {
//        return super.equals(o);
//    }
//
//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }

}
