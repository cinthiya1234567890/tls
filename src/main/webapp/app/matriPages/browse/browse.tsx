import React from 'react';
import axios from 'axios';




type MyProps = {};
type MyState = {};

class Browse extends React.Component<MyProps, MyState> {

  apiUrl = 'api/browse';


  constructor(props) {
    super(props);
  };

  render() {
    return (
      <div>
        browse profiles

      </div>
    );
  }
}



export default Browse;
