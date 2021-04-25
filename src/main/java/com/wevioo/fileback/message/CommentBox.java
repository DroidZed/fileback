package com.wevioo.fileback.message;

import com.wevioo.fileback.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentBox {

    private String commentOwnerImage;
    private String commentOwnerFullName;
    private Comment commentObject;

}
