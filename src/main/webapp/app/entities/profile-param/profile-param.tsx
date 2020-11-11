import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './profile-param.reducer';
import { IProfileParam } from 'app/shared/model/profile-param.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProfileParamProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ProfileParam = (props: IProfileParamProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { profileParamList, match, loading } = props;
  return (
    <div>
      <h2 id="profile-param-heading">
        Profile Params
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Profile Param
        </Link>
      </h2>
      <div className="table-responsive">
        {profileParamList && profileParamList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Data Value</th>
                <th>Data Int</th>
                <th>User Entered Custom Value</th>
                <th>Profile</th>
                <th>Field</th>
                <th>Cascader Param</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {profileParamList.map((profileParam, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${profileParam.id}`} color="link" size="sm">
                      {profileParam.id}
                    </Button>
                  </td>
                  <td>{profileParam.dataValue}</td>
                  <td>{profileParam.dataInt}</td>
                  <td>{profileParam.userEnteredCustomValue}</td>
                  <td>
                    {profileParam.profileName ? <Link to={`profile/${profileParam.profileId}`}>{profileParam.profileName}</Link> : ''}
                  </td>
                  <td>
                    {profileParam.fieldFieldName ? <Link to={`field/${profileParam.fieldId}`}>{profileParam.fieldFieldName}</Link> : ''}
                  </td>
                  <td>
                    {profileParam.cascaderParamParamTitle ? (
                      <Link to={`cascader-param/${profileParam.cascaderParamId}`}>{profileParam.cascaderParamParamTitle}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${profileParam.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${profileParam.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${profileParam.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Profile Params found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ profileParam }: IRootState) => ({
  profileParamList: profileParam.entities,
  loading: profileParam.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProfileParam);
