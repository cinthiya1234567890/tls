import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './issue.reducer';
import { IIssue } from 'app/shared/model/issue.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IIssueDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const IssueDetail = (props: IIssueDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { issueEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Issue [<b>{issueEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="createdTime">Created Time</span>
          </dt>
          <dd>{issueEntity.createdTime ? <TextFormat value={issueEntity.createdTime} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="updatedTime">Updated Time</span>
          </dt>
          <dd>{issueEntity.updatedTime ? <TextFormat value={issueEntity.updatedTime} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{issueEntity.description}</dd>
          <dt>Complaint</dt>
          <dd>{issueEntity.complaintId ? issueEntity.complaintId : ''}</dd>
          <dt>Admin</dt>
          <dd>{issueEntity.adminId ? issueEntity.adminId : ''}</dd>
        </dl>
        <Button tag={Link} to="/issue" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/issue/${issueEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ issue }: IRootState) => ({
  issueEntity: issue.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(IssueDetail);
