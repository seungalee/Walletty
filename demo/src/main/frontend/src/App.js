import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { createContext, React } from "react";
import { UserProvider } from "./context/UserContext";

import Login from "./pages/Login";
import Home from "./pages/Home";
import Join from "./pages/Join";
import Survey from "./pages/Survey";
import Feedback from "./pages/Feedback";
import Mission from "./pages/Mission";
import Survey_goalmoney from "./pages/Survey_goalmoney";
import Vault from "./pages/Vault";
import Toss from "./pages/Toss";
import Profile from "./pages/Profile";
import Statistics from "./pages/Statistics";
import Ranking from "./pages/Ranking";
import AudioTest from "./pages/AudioTest";

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
            <Route path="/feedback" element={<Feedback />} />
            <Route path="/mission" element={<Mission />} />
            <Route path="/vault" element={<Vault />} />
            <Route path="/toss" element={<Toss />} />
            <Route path="/profile" element={<Profile />} />
            <Route path="/statistics" element={<Statistics />} />
            <Route path="/ranking" element={<Ranking />} />
            <Route path="/audiotest" element={<AudioTest />} />
          </Routes>
        </div>
      </BrowserRouter>
    </UserProvider>
  );
}

export default App;
