import React from 'react';
import axios from 'axios';




type MyProps = { };
type MyState = { };

class RegisterProfile extends React.Component<MyProps, MyState> {

  apiUrl = 'api/register_profile';
  

  constructor(props) {
    super(props);
  };

  render() {
    return (
      <div>
        register your profile here

      </div>
    );
  }
}



export default RegisterProfile;
