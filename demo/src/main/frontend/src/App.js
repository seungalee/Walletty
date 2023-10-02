import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { createContext, React } from "react";

import Login from "./pages/Login";
import Home from "./pages/Home";
import Join from "./pages/Join";
import Survey from "./pages/Survey";
import { UserProvider } from "./context/UserContext";
import Survey_goalmoney from "./pages/Survey_goalmoney";

function App() {
  return (
    <UserProvider>
      <BrowserRouter>
        <div className="App">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/join" element={<Join />} />
            <Route path="/survey" element={<Survey />} />
            <Route path="/survey-goalmoney" element={<Survey_goalmoney />} />
          </Routes>
        </div>
      </BrowserRouter>
    </UserProvider>
  );
}

export default App;
