import React, { useContext, useReducer } from "react";

const initialState = {
  user: null,
};

const reducer = (state, action) => {
  switch (action.type) {
    case "LOGIN":
      return {
        ...state,
        user: {
          id: action.userId,
        },
      };
    case "LOGOUT":
      return {
        ...state,
        user: null,
      };
    default:
      return state;
  }
};

const UserStateContext = React.createContext();
const UserDispatchContext = React.createContext();

export const UserProvider = ({ children }) => {
  const [state, dispatch] = useReducer(reducer, initialState);
  return (
    <UserStateContext.Provider value={state}>
      <UserDispatchContext.Provider value={dispatch}>
        {children}
      </UserDispatchContext.Provider>
    </UserStateContext.Provider>
  );
};

export const useUserState = () => {
  const state = useContext(UserStateContext);
  return state;
};

export const useUserDispatch = () => {
  const dispatch = useContext(UserDispatchContext);
  return dispatch;
};
