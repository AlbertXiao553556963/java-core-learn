package com.springEvent;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-11-25 23:08
 **/
@Data
public class OrderCreateEvent extends ApplicationEvent {

    private static final long serialVersionUID = 6802963256668055692L;

    public OrderCreateEvent(Object source) {
        super(source);
    }

}
