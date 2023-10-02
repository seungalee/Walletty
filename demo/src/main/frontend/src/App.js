import React, {useEffect, useState} from 'react';
import axios from 'axios';

function App() {
    const [surveydto, setSurveydto] = useState('')

    useEffect(() => {
        axios.get('/api/v1/surveydto')
            .then(response => setSurveydto(response.data))
            .catch(error => console.log(error))
    }, []);

    return (
        <div>
            surveyId: {surveydto.surveyId} <br />
            fixed_entry: {surveydto.fixed_entry} <br />
            goal_entry_1: {surveydto.goal_entry_1} <br />
            goal_money_1: {surveydto.goal_money_1} <br />
            goal_entry_2: {surveydto.goal_entry_2} <br />
            goal_money_1: {surveydto.goal_money_2} <br />
            goal_entry_3: {surveydto.goal_entry_3} <br />
            goal_money_1: {surveydto.goal_money_3} <br />

        </div>
    );
}

export default App;