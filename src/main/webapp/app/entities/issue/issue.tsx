import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './issue.reducer';
import { IIssue } from 'app/shared/model/issue.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IIssueProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Issue = (props: IIssueProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { issueList, match, loading } = props;
  return (
    <div>
      <h2 id="issue-heading">
        Issues
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Issue
        </Link>
      </h2>
      <div className="table-responsive">
        {issueList && issueList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Created Time</th>
                <th>Updated Time</th>
                <th>Description</th>
                <th>Complaint</th>
                <th>Admin</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {issueList.map((issue, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${issue.id}`} color="link" size="sm">
                      {issue.id}
                    </Button>
                  </td>
                  <td>{issue.createdTime ? <TextFormat type="date" value={issue.createdTime} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{issue.updatedTime ? <TextFormat type="date" value={issue.updatedTime} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{issue.description}</td>
                  <td>{issue.complaintId ? <Link to={`profile/${issue.complaintId}`}>{issue.complaintId}</Link> : ''}</td>
                  <td>{issue.adminId ? <Link to={`profile/${issue.adminId}`}>{issue.adminId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${issue.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${issue.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${issue.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Issues found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ issue }: IRootState) => ({
  issueList: issue.entities,
  loading: issue.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Issue);
