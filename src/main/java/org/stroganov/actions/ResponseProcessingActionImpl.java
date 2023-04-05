package org.stroganov.actions;

import org.springframework.stereotype.Component;
import org.stroganov.ui.UserResponse;

@Component
public class ResponseProcessingActionImpl implements ResponseProcessingAction {



    @Override
    public void handle(UserResponse userResponse) {

        switch (userResponse.getValue()){
            case "1": {
                break;
            }
            case "2":{
                break;
            }

            default:{
                System.out.println();
            }
        }

    }
}
