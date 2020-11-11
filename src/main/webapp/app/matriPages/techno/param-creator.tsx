import React from 'react';
import axios from 'axios';




type MyProps = {  };
type MyState = {
  resultMarkup  : string };

class ParamCreator extends React.Component<MyProps, MyState> {

  apiUrl = 'api/param_creator';
  

  constructor(props) {
    super(props);
    this.state = { resultMarkup: '' };
  };

  upload(data) {
    return axios.post(this.apiUrl + "/upload", data);
  }

  onFileChangeHandler = (e) => {
    const formData = new FormData();
    // alert("num of files=" + e.target.files.length);

    for (let i = 0; i < e.target.files.length; i++) {
      formData.append('files', e.target.files[i])
    }

    // alert("formData length = " + formData.getAll.length);

    this.upload(formData)
      .then(res => {
        // console.log(res.data);
        this.setState({ resultMarkup: res.data });
        // alert("File uploaded successfully. " + res.data);
      })
  };

  render() {
    return (
      <div>
        <label>Upload Files</label>
        <input type="file" className="form-control" name="files"
          onChange={(event) => this.onFileChangeHandler(event)}
          multiple />

        <br />

        <div>
          {<div dangerouslySetInnerHTML={{ __html: this.state.resultMarkup }} />}
        </div>

        <DataDetail></DataDetail>

      </div>
    );
  }
}


class DataDetail extends React.Component {
  render() {
    return (
      <div>
        <br /><br />
        <hr />

        <pre>{`

       


          The data required by the application are captured in text file.
          Upload them using above button and import the data.
          Files are placed at src/model/applicationData

          Here is the description of data collected for matrimonial site.



data per line in txt file
Following files contain single data in each line
countries.txt
language.txt
education.txt
occupation.txt
religions.txt
zodiac.txt

All these data are placed in cascade table.




cascades.txt
some of the profile parameter have cascade as data type.
It is like enumeration.
Such possible values are listed here.  It is a special formatted one.

caste.txt
special, nested data.
This data collected from www.chennaimatrimony.com
The code used is project matrimonydata com.neemshade.matri.scrap.DataDownloader

The above two files are processed and data placed in cascade table.


list_of_cities_and_towns_in_india-834j.csv
csv from www.downloadexcelfiles.com
2nd and 3rd data in a row has city and state detail
These data will be uploaded to cascade table.



paramNames.txt
has a special formatted data.  Each field parameter has an entry in this file.
This file need to be loaded after the cascade data.

The field name is before hypen.  What comes after hyphen are field attributes.
A description may be placed within quotes.

If the field name is already present in the db, the record is skipped.
If you want changes in the attributes,
write the keyword replace in the attributes section.
          
          
        `}</pre>
      </div>
    );
  }
}


export default ParamCreator;
